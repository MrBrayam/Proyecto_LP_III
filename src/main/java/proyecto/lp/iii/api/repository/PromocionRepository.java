package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    List<Promocion> findByTenant(Tenant tenant);
    List<Promocion> findByTenantAndEstado(Tenant tenant, Promocion.EstadoPromocion estado);
}
