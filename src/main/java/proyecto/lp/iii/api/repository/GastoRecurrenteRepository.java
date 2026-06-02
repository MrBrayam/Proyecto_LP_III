package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoRecurrente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoRecurrenteRepository extends JpaRepository<GastoRecurrente, Integer> {

}
