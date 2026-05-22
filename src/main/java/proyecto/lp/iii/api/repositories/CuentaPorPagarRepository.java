package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.CuentaPorPagar;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagar, Integer> {
    List<CuentaPorPagar> findByTenant(Tenant tenant);
    List<CuentaPorPagar> findByTenantAndEstadoPago(Tenant tenant, CuentaPorPagar.EstadoPago estado);
    List<CuentaPorPagar> findByProveedor(Proveedor proveedor);
}
