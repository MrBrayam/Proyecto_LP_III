package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FacturaSuscripcion;
import proyecto.lp.iii.api.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaSuscripcionRepository extends JpaRepository<FacturaSuscripcion, Integer> {
    List<FacturaSuscripcion> findBySuscripcion(Suscripcion suscripcion);
}
