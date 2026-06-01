package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ImagenProducto;
import proyecto.lp.iii.api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {
    @Query("SELECT i FROM ImagenProducto i WHERE i.id_productos = :producto")
    List<ImagenProducto> findByProducto(@Param("producto") Producto producto);

    @Query("SELECT i FROM ImagenProducto i WHERE i.id_productos = :producto AND i.es_principal = :esPrincipal")
    List<ImagenProducto> findByProductoAndEsPrincipal(@Param("producto") Producto producto, @Param("esPrincipal") Boolean esPrincipal);
}
