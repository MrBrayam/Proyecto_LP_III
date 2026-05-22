package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Sede;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByTenant(Tenant tenant);
    List<Sede> findByTenantAndEstado(Tenant tenant, Sede.EstadoSede estado);
}
