package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import proyecto.lp.iii.api.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormaPagoVentaRepository extends JpaRepository<FormaPagoVenta, Integer> {
    @Query("SELECT f FROM FormaPagoVenta f WHERE f.id_ventas = :venta")
    List<FormaPagoVenta> findByVenta(@Param("venta") Venta venta);
}
