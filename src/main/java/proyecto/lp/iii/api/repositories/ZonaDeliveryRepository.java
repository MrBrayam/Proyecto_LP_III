package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ZonaDelivery;
import proyecto.lp.iii.api.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZonaDeliveryRepository extends JpaRepository<ZonaDelivery, Integer> {
    List<ZonaDelivery> findBySede(Sede sede);
    List<ZonaDelivery> findBySedeAndEstado(Sede sede, ZonaDelivery.EstadoZona estado);
}
