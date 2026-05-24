package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import proyecto.lp.iii.api.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormaPagoVentaRepository extends JpaRepository<FormaPagoVenta, Integer> {
    List<FormaPagoVenta> findByVenta(Venta venta);
}
