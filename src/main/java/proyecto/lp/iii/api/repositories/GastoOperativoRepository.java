package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.GastoOperativo;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoOperativoRepository extends JpaRepository<GastoOperativo, Integer> {
    List<GastoOperativo> findByTenant(Tenant tenant);
    List<GastoOperativo> findByTenantAndEstado(Tenant tenant, GastoOperativo.EstadoGasto estado);
}
