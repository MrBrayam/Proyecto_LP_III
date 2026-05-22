package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.RolPersonalizado;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolPersonalizadoRepository extends JpaRepository<RolPersonalizado, Integer> {
    List<RolPersonalizado> findByTenant(Tenant tenant);
    List<RolPersonalizado> findByTenantAndEstado(Tenant tenant, RolPersonalizado.EstadoRol estado);
}
