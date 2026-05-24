package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoRecurrente;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoRecurrenteRepository extends JpaRepository<GastoRecurrente, Integer> {
    List<GastoRecurrente> findByTenant(Tenant tenant);
    List<GastoRecurrente> findByTenantAndEstado(Tenant tenant, GastoRecurrente.EstadoRecurrente estado);
}
