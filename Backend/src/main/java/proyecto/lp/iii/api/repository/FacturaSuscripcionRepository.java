package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FacturaSuscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaSuscripcionRepository extends JpaRepository<FacturaSuscripcion, Integer> {

}
