## Plan: Align Project to Tenants Architecture (No Renaming)
Usar como guia la base de datos llamada bellarista_mysql.sql. para no haya equivocacion en los datos

Align all controllers, services, repositories, and entities to match the Tenants/TenantController structure, without renaming classes. Keep snake_case field names and use soft delete for entities with estado.

Steps
1. Inventory current layers: list controllers, services (interfaces + implementations), repositories, and entities; categorize missing vs present patterns.
2. Define canonical templates from marked files:
   - Controller: TenantController
   - Service interface: ITenantsService
   - Service implementation: TenantsService
   - Repository: TenantsRepository
   - Entity: Tenants (manual getters/setters, @SQLDelete/@SQLRestriction, @JsonPropertyOrder, snake_case fields)
3. Standardize entity relationships:
   - Replace any @ManyToOne fields that point to Integer IDs with entity references (Tenants) and @JoinColumn name mapping.
   - Ensure all entities with estado use @SQLDelete + @SQLRestriction (estado=1) and default estado=1.
4. Apply entity template across all entities:
   - Keep snake_case field names to match DB columns.
   - Add @Column for length/nullability where needed.
   - Add @JsonIgnoreProperties for lazy relations.
   - Ensure @JsonPropertyOrder lists all fields in order.
5. Apply controller template across all controllers:
   - CRUD endpoints using /api/{entity} and methods buscarTodos/guardar/modificar/buscarId/eliminar.
6. Apply service template across all services:
   - Add missing I{Entity}Service and {Entity}Service in /service/jpa/.
7. Apply repository template across all repositories:
   - Add missing JpaRepository interfaces for entities without them.
8. Update wiring and fix compile errors:
   - Ensure controller/service types match entity names (no renaming).
9. Validate:
   - Run the app; fix any remaining JPA mapping errors and endpoint wiring issues.

Relevant files
- src/main/java/proyecto/lp/iii/api/controller/TenantController.java
- src/main/java/proyecto/lp/iii/api/service/ITenantsService.java
- src/main/java/proyecto/lp/iii/api/service/jpa/TenantsService.java
- src/main/java/proyecto/lp/iii/api/repository/TenantsRepository.java
- src/main/java/proyecto/lp/iii/api/entity/Tenants.java
- src/main/java/proyecto/lp/iii/api/entity/Usuarios.java

Verification
1. Start the app and ensure JPA initializes without entity mapping errors.
2. Test a representative CRUD endpoint (e.g., GET /api/tenants).
3. Run a full CRUD flow on one entity to confirm wiring.

Decisions
- No renaming of entities or classes.
- Architecture standard follows the Tenants/TenantController/Service/Repository structure.
- Soft delete enabled for entities with estado using @SQLDelete/@SQLRestriction.
- Tenant relations use @ManyToOne to the Tenants entity.

