package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.entity.DevolucionProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionProveedorRepository extends JpaRepository<DetalleDevolucionProveedor, Integer> {
    List<DetalleDevolucionProveedor> findByDevolucion(DevolucionProveedor devolucion);
}
