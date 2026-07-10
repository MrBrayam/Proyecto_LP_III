/*
 * package proyecto.lp.iii.api.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.boot.CommandLineRunner;
 * import org.springframework.stereotype.Component;
 * import proyecto.lp.iii.api.entity.Tenants;
 * import proyecto.lp.iii.api.entity.RolPersonalizado;
 * import proyecto.lp.iii.api.entity.PermisoRol;
 * import proyecto.lp.iii.api.repository.TenantsRepository;
 * import proyecto.lp.iii.api.repository.RolPersonalizadoRepository;
 * import proyecto.lp.iii.api.repository.PermisoRolRepository;
 * 
 * import java.util.List;
 * import java.util.Optional;
 * 
 * @Component
 * public class DatabaseSeeder implements CommandLineRunner {
 * 
 * @Autowired
 * private TenantsRepository tenantsRepository;
 * 
 * @Autowired
 * private RolPersonalizadoRepository rolPersonalizadoRepository;
 * 
 * @Autowired
 * private PermisoRolRepository permisoRolRepository;
 * 
 * @Override
 * public void run(String... args) throws Exception {
 * List<Tenants> tenants = tenantsRepository.findAll();
 * for (Tenants tenant : tenants) {
 * seedRolesForTenant(tenant);
 * }
 * }
 * 
 * private void seedRolesForTenant(Tenants tenant) {
 * // 1. Cajero
 * RolPersonalizado cajero = getOrCreateRol(tenant, "cajero",
 * "Acceso a caja, cobros y ventas del negocio");
 * seedPermisosForRol(cajero, List.of("ventas", "pedidos", "formas-pago-venta",
 * "comprobantes-electronicos", "series-comprobantes", "caja-chica",
 * "sesiones-caja", "metodos-pago", "clientes"));
 * 
 * // 2. Estilista
 * RolPersonalizado estilista = getOrCreateRol(tenant, "estilista",
 * "Especialista autorizado en estilismo y citas");
 * seedPermisosForRol(estilista, List.of("citas", "clientes", "productos",
 * "categorias", "marcas"));
 * 
 * // 3. Repartidor
 * RolPersonalizado repartidor = getOrCreateRol(tenant, "repartidor",
 * "Encargado de la entrega de pedidos a domicilio");
 * seedPermisosForRol(repartidor, List.of("pedidos", "clientes",
 * "zonas-delivery"));
 * 
 * // 4. Recepcionista
 * RolPersonalizado recepcionista = getOrCreateRol(tenant, "recepcionista",
 * "Administración de citas y recepción de clientes");
 * seedPermisosForRol(recepcionista, List.of("citas", "clientes",
 * "notificaciones", "horarios-operacion"));
 * }
 * 
 * private RolPersonalizado getOrCreateRol(Tenants tenant, String nombreRol,
 * String descripcion) {
 * Optional<RolPersonalizado> existing =
 * rolPersonalizadoRepository.findAll().stream()
 * .filter(r -> r.getId_tenants() != null &&
 * r.getId_tenants().getId_tenants().equals(tenant.getId_tenants())
 * && r.getNombre_rol_personalizado() != null
 * && r.getNombre_rol_personalizado().equalsIgnoreCase(nombreRol))
 * .findFirst();
 * 
 * if (existing.isPresent()) {
 * return existing.get();
 * }
 * 
 * RolPersonalizado newRol = new RolPersonalizado();
 * newRol.setId_tenants(tenant);
 * newRol.setNombre_rol_personalizado(nombreRol);
 * newRol.setDescripcion(descripcion);
 * newRol.setEstado(1);
 * return rolPersonalizadoRepository.save(newRol);
 * }
 * 
 * private void seedPermisosForRol(RolPersonalizado rol, List<String> modulos) {
 * List<PermisoRol> existingPerms = permisoRolRepository.findAll().stream()
 * .filter(p -> p.getId_roles_personalizados() != null
 * && p.getId_roles_personalizados().getId_roles_personalizados().equals(rol.
 * getId_roles_personalizados()))
 * .toList();
 * 
 * for (String modulo : modulos) {
 * boolean exists = existingPerms.stream()
 * .anyMatch(p -> p.getModulo() != null &&
 * p.getModulo().equalsIgnoreCase(modulo));
 * 
 * if (!exists) {
 * PermisoRol newPerm = new PermisoRol();
 * newPerm.setId_roles_personalizados(rol);
 * newPerm.setModulo(modulo);
 * newPerm.setAccion("visualizar");
 * newPerm.setRecurso("modulo");
 * newPerm.setEstado(1);
 * permisoRolRepository.save(newPerm);
 * }
 * }
 * }
 * }
 */