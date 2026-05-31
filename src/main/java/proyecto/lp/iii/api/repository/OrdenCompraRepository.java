package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.OrdenCompra;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {
    List<OrdenCompra> findByTenant(Tenants tenant);

    List<OrdenCompra> findByProveedor(Proveedor proveedor);
}
