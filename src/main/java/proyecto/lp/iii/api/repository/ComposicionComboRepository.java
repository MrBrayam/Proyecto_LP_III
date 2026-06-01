package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.entity.ComboPromocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComposicionComboRepository extends JpaRepository<ComposicionCombo, Integer> {
    @Query("SELECT c FROM ComposicionCombo c WHERE c.id_combos_promocionales = :combo")
    List<ComposicionCombo> findByCombo(@Param("combo") ComboPromocional combo);
}
