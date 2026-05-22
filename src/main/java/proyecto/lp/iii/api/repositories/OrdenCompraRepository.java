package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.OrdenCompra;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {
    List<OrdenCompra> findByTenant(Tenant tenant);
    List<OrdenCompra> findByTenantAndEstado(Tenant tenant, OrdenCompra.EstadoOrden estado);
    List<OrdenCompra> findByProveedor(Proveedor proveedor);
}
