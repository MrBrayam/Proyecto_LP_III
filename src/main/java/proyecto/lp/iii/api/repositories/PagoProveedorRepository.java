package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.PagoProveedor;
import proyecto.lp.iii.api.entities.CuentaPorPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, Integer> {
    List<PagoProveedor> findByCuentaPorPagar(CuentaPorPagar cuenta);
}
