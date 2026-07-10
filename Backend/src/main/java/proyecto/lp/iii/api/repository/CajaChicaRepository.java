package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CajaChica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaChicaRepository extends JpaRepository<CajaChica, Integer> {

}