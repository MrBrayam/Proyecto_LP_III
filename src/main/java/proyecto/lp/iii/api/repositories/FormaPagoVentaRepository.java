package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.FormaPagoVenta;
import proyecto.lp.iii.api.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FormaPagoVentaRepository extends JpaRepository<FormaPagoVenta, Integer> {
    List<FormaPagoVenta> findByVenta(Venta venta);
}
