package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.FacturaSuscripcion;
import proyecto.lp.iii.api.entities.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaSuscripcionRepository extends JpaRepository<FacturaSuscripcion, Integer> {
    List<FacturaSuscripcion> findBySuscripcion(Suscripcion suscripcion);
    List<FacturaSuscripcion> findByEstadoPago(FacturaSuscripcion.EstadoPago estado);
}
