package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PlanSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcion, Integer> {
    @Query("SELECT p FROM PlanSuscripcion p WHERE LOWER(p.nombre_plan_suscripcion) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<PlanSuscripcion> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);
}
