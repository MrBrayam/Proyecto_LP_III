package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ServicioCita;
import proyecto.lp.iii.api.entities.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioCitaRepository extends JpaRepository<ServicioCita, Integer> {
    List<ServicioCita> findByCita(Cita cita);
}
