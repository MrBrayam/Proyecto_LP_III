package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CajaChica;
import proyecto.lp.iii.api.entity.SesionCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CajaChicaRepository extends JpaRepository<CajaChica, Integer> {
    @Query("SELECT c FROM CajaChica c WHERE c.id_sesiones_caja = :sesion")
    List<CajaChica> findBySesionCaja(@Param("sesion") SesionCaja sesion);
}





