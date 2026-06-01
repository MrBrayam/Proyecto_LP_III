package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CuentaPorPagarRepository extends JpaRepository<CuentaPorPagar, Integer> {
    @Query("SELECT c FROM CuentaPorPagar c WHERE c.id_tenants = :tenant")
    List<CuentaPorPagar> findByTenant(@Param("tenant") Tenants tenant);
    @Query("SELECT c FROM CuentaPorPagar c WHERE c.id_proveedores = :proveedor")
    List<CuentaPorPagar> findByProveedor(@Param("proveedor") Proveedor proveedor);
}
