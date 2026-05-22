package proyecto.lp.iii.api.repositories;


import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Integer> {
    Optional<Tenant> findByRuc(String ruc);
    Optional<Tenant> findByCorreo(String correo);
    List<Tenant> findByEstado(Tenant.EstadoTenant estado);
}
