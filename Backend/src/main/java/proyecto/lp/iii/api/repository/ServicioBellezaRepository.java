package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ServicioBelleza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicioBellezaRepository extends JpaRepository<ServicioBelleza, Integer> {

}
