package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ZonaDelivery;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZonaDeliveryRepository extends JpaRepository<ZonaDelivery, Integer> {
    List<ZonaDelivery> findBySede(Sede sede);
}
