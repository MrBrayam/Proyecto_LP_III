package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {

}
