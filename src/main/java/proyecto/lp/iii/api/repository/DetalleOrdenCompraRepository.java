package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetalleOrdenCompra;
import proyecto.lp.iii.api.entity.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetalleOrdenCompraRepository extends JpaRepository<DetalleOrdenCompra, Integer> {
    @Query("SELECT d FROM DetalleOrdenCompra d WHERE d.id_ordenes_compra = :orden")
    List<DetalleOrdenCompra> findByOrdenCompra(@Param("orden") OrdenCompra orden);
}
