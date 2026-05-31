package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagar, Integer> {
    List<CuentaPorPagar> findByTenant(Tenants tenant);
    List<CuentaPorPagar> findByTenantAndEstadoPago(Tenants tenant, CuentaPorPagar.EstadoPago estado);
    List<CuentaPorPagar> findByProveedor(Proveedor proveedor);
}
