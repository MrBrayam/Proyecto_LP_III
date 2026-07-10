package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ServicioCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioCitaRepository extends JpaRepository<ServicioCita, Integer> {

}
