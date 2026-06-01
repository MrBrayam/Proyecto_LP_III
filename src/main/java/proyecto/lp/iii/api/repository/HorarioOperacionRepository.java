package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.HorarioOperacion;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HorarioOperacionRepository extends JpaRepository<HorarioOperacion, Integer> {
    @Query("SELECT h FROM HorarioOperacion h WHERE h.id_sedes = :sede")
    List<HorarioOperacion> findBySede(@Param("sede") Sede sede);
}
