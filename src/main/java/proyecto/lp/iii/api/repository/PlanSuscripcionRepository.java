package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PlanSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcion, Integer> {
    List<PlanSuscripcion> findByNombreContainingIgnoreCase(String nombre);
}
