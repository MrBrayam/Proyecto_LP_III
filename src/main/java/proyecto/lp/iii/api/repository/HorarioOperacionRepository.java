package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.HorarioOperacion;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioOperacionRepository extends JpaRepository<HorarioOperacion, Integer> {
    List<HorarioOperacion> findBySede(Sede sede);
}
