package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoRecurrente;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoRecurrenteRepository extends JpaRepository<GastoRecurrente, Integer> {
    List<GastoRecurrente> findByTenant(Tenants tenant);
    List<GastoRecurrente> findByTenantAndEstado(Tenants tenant, GastoRecurrente.EstadoRecurrente estado);
}
