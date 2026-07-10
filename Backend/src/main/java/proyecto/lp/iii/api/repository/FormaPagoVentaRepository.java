package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoVentaRepository extends JpaRepository<FormaPagoVenta, Integer> {

}
