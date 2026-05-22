package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.CajaChica;
import proyecto.lp.iii.api.entities.SesionCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CajaChicaRepository extends JpaRepository<CajaChica, Integer> {
    List<CajaChica> findBySesionCaja(SesionCaja sesion);
}





