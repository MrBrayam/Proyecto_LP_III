package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleDevolucionVentaRepository extends JpaRepository<DetalleDevolucionVenta, Integer> {

}
