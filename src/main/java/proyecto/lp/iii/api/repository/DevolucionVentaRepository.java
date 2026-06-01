package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DevolucionVenta;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DevolucionVentaRepository extends JpaRepository<DevolucionVenta, Integer> {
    @Query("SELECT d FROM DevolucionVenta d WHERE d.id_tenants = :tenant")
    List<DevolucionVenta> findByTenant(@Param("tenant") Tenants tenant);
    @Query("SELECT d FROM DevolucionVenta d WHERE d.id_ventas = :venta")
    List<DevolucionVenta> findByVenta(@Param("venta") Venta venta);
}
