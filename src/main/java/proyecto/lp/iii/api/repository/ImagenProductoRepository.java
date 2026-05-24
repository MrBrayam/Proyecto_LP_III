package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ImagenProducto;
import proyecto.lp.iii.api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {
    List<ImagenProducto> findByProducto(Producto producto);
    List<ImagenProducto> findByProductoAndEsPrincipal(Producto producto, Boolean esPrincipal);
}
