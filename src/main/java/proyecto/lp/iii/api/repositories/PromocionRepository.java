package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Promocion;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    List<Promocion> findByTenant(Tenant tenant);
    List<Promocion> findByTenantAndEstado(Tenant tenant, Promocion.EstadoPromocion estado);
}
