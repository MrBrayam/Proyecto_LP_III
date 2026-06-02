package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DevolucionVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucionVentaRepository extends JpaRepository<DevolucionVenta, Integer> {
}
