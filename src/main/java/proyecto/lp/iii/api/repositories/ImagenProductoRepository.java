package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ImagenProducto;
import proyecto.lp.iii.api.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImagenProductoRepository extends JpaRepository<ImagenProducto, Integer> {
    List<ImagenProducto> findByProducto(Producto producto);
    List<ImagenProducto> findByProductoAndEsPrincipal(Producto producto, Boolean esPrincipal);
}
