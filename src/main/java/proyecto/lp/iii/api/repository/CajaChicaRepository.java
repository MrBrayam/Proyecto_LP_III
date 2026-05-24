package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CajaChica;
import proyecto.lp.iii.api.entity.SesionCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CajaChicaRepository extends JpaRepository<CajaChica, Integer> {
    List<CajaChica> findBySesionCaja(SesionCaja sesion);
}





