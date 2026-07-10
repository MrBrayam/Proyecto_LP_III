package proyecto.lp.iii.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.lp.iii.api.entity.PreciosPlan;

@Repository
public interface PreciosPlanRepository extends JpaRepository<PreciosPlan, Integer> {

}
