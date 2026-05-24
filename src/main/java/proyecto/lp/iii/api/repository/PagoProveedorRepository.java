package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.entity.CuentaPorPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, Integer> {
    List<PagoProveedor> findByCuentaPorPagar(CuentaPorPagar cuenta);
}
