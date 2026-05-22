package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.entities.DevolucionProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleDevolucionProveedorRepository extends JpaRepository<DetalleDevolucionProveedor, Integer> {
    List<DetalleDevolucionProveedor> findByDevolucion(DevolucionProveedor devolucion);
}
