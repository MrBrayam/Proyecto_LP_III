package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ComposicionCombo;
import proyecto.lp.iii.api.entities.ComboPromocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComposicionComboRepository extends JpaRepository<ComposicionCombo, Integer> {
    List<ComposicionCombo> findByCombo(ComboPromocional combo);
}
