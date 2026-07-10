package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ZonaDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaDeliveryRepository extends JpaRepository<ZonaDelivery, Integer> {

}
