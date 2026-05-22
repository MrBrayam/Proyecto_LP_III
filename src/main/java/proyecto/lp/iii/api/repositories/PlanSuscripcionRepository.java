package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.PlanSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanSuscripcionRepository extends JpaRepository<PlanSuscripcion, Integer> {
    List<PlanSuscripcion> findByEstado(PlanSuscripcion.EstadoPlan estado);
    List<PlanSuscripcion> findByNombreContainingIgnoreCase(String nombre);
}
