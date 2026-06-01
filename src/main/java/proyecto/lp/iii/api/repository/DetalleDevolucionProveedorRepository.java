package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.entity.DevolucionProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionProveedorRepository extends JpaRepository<DetalleDevolucionProveedor, Integer> {
    @Query("SELECT d FROM DetalleDevolucionProveedor d WHERE d.id_devoluciones_proveedor = :devolucion")
    List<DetalleDevolucionProveedor> findByDevolucion(@Param("devolucion") DevolucionProveedor devolucion);
}
