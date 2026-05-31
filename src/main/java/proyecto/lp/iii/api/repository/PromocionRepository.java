package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    List<Promocion> findByTenant(Tenants tenant);
    List<Promocion> findByTenantAndEstado(Tenants tenant, Promocion.EstadoPromocion estado);
}
