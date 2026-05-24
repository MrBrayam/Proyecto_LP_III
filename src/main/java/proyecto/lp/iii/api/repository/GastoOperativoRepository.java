package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoOperativo;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoOperativoRepository extends JpaRepository<GastoOperativo, Integer> {
    List<GastoOperativo> findByTenant(Tenant tenant);
    List<GastoOperativo> findByTenantAndEstado(Tenant tenant, GastoOperativo.EstadoGasto estado);
}
