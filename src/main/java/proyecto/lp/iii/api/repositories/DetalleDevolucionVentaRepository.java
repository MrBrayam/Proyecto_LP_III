package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DetalleDevolucionVenta;
import proyecto.lp.iii.api.entities.DevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionVentaRepository extends JpaRepository<DetalleDevolucionVenta, Integer> {
    List<DetalleDevolucionVenta> findByDevolucion(DevolucionVenta devolucion);
}
