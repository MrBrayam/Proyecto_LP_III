package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DevolucionProveedor;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DevolucionProveedorRepository extends JpaRepository<DevolucionProveedor, Integer> {
    List<DevolucionProveedor> findByTenant(Tenants tenant);
    List<DevolucionProveedor> findByProveedor(Proveedor proveedor);
    List<DevolucionProveedor> findByEstado(DevolucionProveedor.EstadoDevolucion estado);
}
