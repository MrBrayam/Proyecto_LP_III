package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.MovimientoInventario;
import proyecto.lp.iii.api.entities.LoteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findByLote(LoteInventario lote);
    List<MovimientoInventario> findByTipoMovimiento(MovimientoInventario.TipoMovimiento tipo);
}
