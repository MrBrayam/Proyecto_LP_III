package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DetalleOrdenCompra;
import proyecto.lp.iii.api.entities.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, Integer> {
    List<DetalleOrdenCompra> findByOrdenCompra(OrdenCompra orden);
}
