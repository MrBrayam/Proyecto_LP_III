package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Integer> {
    List<ComprobanteElectronico> findByTenant(Tenants tenant);
}
