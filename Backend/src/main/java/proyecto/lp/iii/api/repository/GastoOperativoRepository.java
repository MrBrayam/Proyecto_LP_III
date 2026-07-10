package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoOperativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoOperativoRepository extends JpaRepository<GastoOperativo, Integer> {

}
