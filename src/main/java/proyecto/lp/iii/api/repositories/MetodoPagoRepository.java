package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.MetodoPago;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    List<MetodoPago> findByTenant(Tenant tenant);
    List<MetodoPago> findByTenantAndEstado(Tenant tenant, MetodoPago.EstadoMetodo estado);
}
