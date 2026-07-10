package proyecto.lp.iii.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyecto.lp.iii.api.entity.ComposicionCombo;

@Repository
public interface ComposicionComboRepository extends JpaRepository<ComposicionCombo, Integer> {

    @Query("SELECT c FROM ComposicionCombo c WHERE c.id_combos_promocionales.id_combos_promocionales = :comboId")
    List<ComposicionCombo> findByComboId(@Param("comboId") Integer comboId);

    @Modifying
    @Query("DELETE FROM ComposicionCombo c WHERE c.id_combos_promocionales.id_combos_promocionales = :comboId")
    void deleteByComboId(@Param("comboId") Integer comboId);
}
