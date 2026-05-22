package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DevolucionProveedor;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DevolucionProveedorRepository extends JpaRepository<DevolucionProveedor, Integer> {
    List<DevolucionProveedor> findByTenant(Tenant tenant);
    List<DevolucionProveedor> findByProveedor(Proveedor proveedor);
    List<DevolucionProveedor> findByEstado(DevolucionProveedor.EstadoDevolucion estado);
}
