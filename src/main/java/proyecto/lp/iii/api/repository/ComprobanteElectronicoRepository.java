package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComprobanteElectronicoRepository extends JpaRepository<ComprobanteElectronico, Integer> {
    @Query("SELECT c FROM ComprobanteElectronico c WHERE c.id_tenants = :tenant")
    List<ComprobanteElectronico> findByTenant(@Param("tenant") Tenants tenant);
}
