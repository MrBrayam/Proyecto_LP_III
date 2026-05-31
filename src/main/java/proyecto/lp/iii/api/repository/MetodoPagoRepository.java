package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.MetodoPago;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    List<MetodoPago> findByTenant(Tenants tenant);
    List<MetodoPago> findByTenantAndEstado(Tenants tenant, MetodoPago.EstadoMetodo estado);
}
