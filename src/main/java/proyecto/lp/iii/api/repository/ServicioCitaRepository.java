package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ServicioCita;
import proyecto.lp.iii.api.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioCitaRepository extends JpaRepository<ServicioCita, Integer> {
    @Query("SELECT s FROM ServicioCita s WHERE s.id_citas = :cita")
    List<ServicioCita> findByCita(@Param("cita") Cita cita);
}
