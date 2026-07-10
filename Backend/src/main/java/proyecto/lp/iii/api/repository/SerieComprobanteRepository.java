package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SerieComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieComprobanteRepository extends JpaRepository<SerieComprobante, Integer> {

}
