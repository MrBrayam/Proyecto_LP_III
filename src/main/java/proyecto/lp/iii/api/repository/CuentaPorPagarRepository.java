package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.entity.Tenant;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagar, Integer> {
    List<CuentaPorPagar> findByTenant(Tenant tenant);
    List<CuentaPorPagar> findByTenantAndEstadoPago(Tenant tenant, CuentaPorPagar.EstadoPago estado);
    List<CuentaPorPagar> findByProveedor(Proveedor proveedor);
}
