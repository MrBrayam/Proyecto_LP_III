package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.LoteInventario;
import proyecto.lp.iii.api.entities.Producto;
import proyecto.lp.iii.api.entities.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoteInventarioRepository extends JpaRepository<LoteInventario, Integer> {
    List<LoteInventario> findByProducto(Producto producto);
    List<LoteInventario> findByAlmacen(Almacen almacen);
    List<LoteInventario> findByProductoAndEstado(Producto producto, LoteInventario.EstadoLote estado);
    List<LoteInventario> findByEstado(LoteInventario.EstadoLote estado);
}
