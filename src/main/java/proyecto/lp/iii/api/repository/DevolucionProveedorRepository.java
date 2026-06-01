package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DevolucionProveedor;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DevolucionProveedorRepository extends JpaRepository<DevolucionProveedor, Integer> {
    @Query("SELECT d FROM DevolucionProveedor d WHERE d.id_tenants = :tenant")
    List<DevolucionProveedor> findByTenant(@Param("tenant") Tenants tenant);
    @Query("SELECT d FROM DevolucionProveedor d WHERE d.id_proveedores = :proveedor")
    List<DevolucionProveedor> findByProveedor(@Param("proveedor") Proveedor proveedor);
}
