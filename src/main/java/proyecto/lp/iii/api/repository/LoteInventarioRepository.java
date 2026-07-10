package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.LoteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import proyecto.lp.iii.api.entity.Producto;

@Repository
public interface LoteInventarioRepository extends JpaRepository<LoteInventario, Integer> {
    @Query("SELECT l FROM LoteInventario l WHERE l.id_productos = :producto AND l.estado = 1 AND l.cantidad_disponible > 0 ORDER BY l.id_lotes_inventario ASC")
    List<LoteInventario> findAvailableLotsByProducto(@Param("producto") Producto producto);

    @Query("SELECT l FROM LoteInventario l WHERE l.id_productos = :producto AND l.estado = 1")
    List<LoteInventario> findLotsByProducto(@Param("producto") Producto producto);
}
