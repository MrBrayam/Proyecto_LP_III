package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.HorarioOperacion;
import proyecto.lp.iii.api.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioOperacionRepository extends JpaRepository<HorarioOperacion, Integer> {
    List<HorarioOperacion> findBySede(Sede sede);
    List<HorarioOperacion> findBySedeAndDiaSemana(Sede sede, HorarioOperacion.DiaSemana diaSemana);
}
