package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Integer> {

}
