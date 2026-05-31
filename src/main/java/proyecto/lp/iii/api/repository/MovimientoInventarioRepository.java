package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.entity.LoteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    List<MovimientoInventario> findByLote(LoteInventario lote);
}