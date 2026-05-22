package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DetalleVenta;
import proyecto.lp.iii.api.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    List<DetalleVenta> findByVenta(Venta venta);
}
