package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import proyecto.lp.iii.api.entity.DevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionVentaRepository extends JpaRepository<DetalleDevolucionVenta, Integer> {
    List<DetalleDevolucionVenta> findByDevolucion(DevolucionVenta devolucion);
}
