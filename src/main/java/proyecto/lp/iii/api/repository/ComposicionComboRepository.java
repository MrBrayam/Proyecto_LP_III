package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.entity.ComboPromocional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComposicionComboRepository extends JpaRepository<ComposicionCombo, Integer> {
    List<ComposicionCombo> findByCombo(ComboPromocional combo);
}
