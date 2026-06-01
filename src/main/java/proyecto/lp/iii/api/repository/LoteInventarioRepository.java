package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoteInventarioRepository extends JpaRepository<LoteInventario, Integer> {
    @Query("SELECT l FROM LoteInventario l WHERE l.id_productos = :producto")
    List<LoteInventario> findByProducto(@Param("producto") Producto producto);

    @Query("SELECT l FROM LoteInventario l WHERE l.id_almacenes = :almacen")
    List<LoteInventario> findByAlmacen(@Param("almacen") Almacen almacen);
}
