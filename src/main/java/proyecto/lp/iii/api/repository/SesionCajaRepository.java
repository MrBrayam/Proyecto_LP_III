package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SesionCaja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionCajaRepository extends JpaRepository<SesionCaja, Integer> {

}
