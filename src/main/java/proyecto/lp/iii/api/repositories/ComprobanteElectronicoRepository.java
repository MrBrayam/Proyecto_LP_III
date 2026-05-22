package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ComprobanteElectronico;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Integer> {
    List<ComprobanteElectronico> findByTenant(Tenant tenant);
    List<ComprobanteElectronico> findByEstadoSunat(ComprobanteElectronico.EstadoSunat estado);
}
