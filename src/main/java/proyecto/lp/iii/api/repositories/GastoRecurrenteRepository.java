package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.GastoRecurrente;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoRecurrenteRepository extends JpaRepository<GastoRecurrente, Integer> {
    List<GastoRecurrente> findByTenant(Tenant tenant);
    List<GastoRecurrente> findByTenantAndEstado(Tenant tenant, GastoRecurrente.EstadoRecurrente estado);
}
