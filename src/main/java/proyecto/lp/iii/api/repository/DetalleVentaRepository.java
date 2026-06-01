package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Integer> {
    @Query("SELECT d FROM DetalleVenta d WHERE d.id_ventas = :venta")
    List<DetalleVenta> findByVenta(@Param("venta") Venta venta);
}
