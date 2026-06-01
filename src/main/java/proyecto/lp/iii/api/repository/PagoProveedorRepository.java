package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.entity.CuentaPorPagar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, Integer> {
    @Query("SELECT p FROM PagoProveedor p WHERE p.id_cuentas_por_pagar = :cuenta")
    List<PagoProveedor> findByCuentaPorPagar(@Param("cuenta") CuentaPorPagar cuenta);
}
