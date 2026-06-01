package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.entity.LoteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, Integer> {
    @Query("SELECT m FROM MovimientoInventario m WHERE m.id_lotes_inventario = :lote")
    List<MovimientoInventario> findByLote(@Param("lote") LoteInventario lote);
}