package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DevolucionVenta;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DevolucionVentaRepository extends JpaRepository<DevolucionVenta, Integer> {
    List<DevolucionVenta> findByTenant(Tenant tenant);
    List<DevolucionVenta> findByVenta(Venta venta);
    List<DevolucionVenta> findByEstadoDevolucion(DevolucionVenta.EstadoDevolucion estado);
}
