package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import proyecto.lp.iii.api.entity.DevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionVentaRepository extends JpaRepository<DetalleDevolucionVenta, Integer> {
    @Query("SELECT d FROM DetalleDevolucionVenta d WHERE d.id_devoluciones_venta = :devolucion")
    List<DetalleDevolucionVenta> findByDevolucion(@Param("devolucion") DevolucionVenta devolucion);
}
