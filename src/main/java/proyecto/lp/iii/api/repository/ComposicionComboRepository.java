package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComposicionCombo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComposicionComboRepository extends JpaRepository<ComposicionCombo, Integer> {

}
