package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.Almacen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoteInventarioRepository extends JpaRepository<LoteInventario, Integer> {
    List<LoteInventario> findByProducto(Producto producto);

    List<LoteInventario> findByAlmacen(Almacen almacen);

}
