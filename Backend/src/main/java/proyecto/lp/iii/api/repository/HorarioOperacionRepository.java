package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.HorarioOperacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioOperacionRepository extends JpaRepository<HorarioOperacion, Integer> {

}
