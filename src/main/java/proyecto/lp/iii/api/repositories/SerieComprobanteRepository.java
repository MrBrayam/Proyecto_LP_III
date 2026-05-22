package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.SerieComprobante;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SerieComprobanteRepository extends JpaRepository<SerieComprobante, Integer> {
    List<SerieComprobante> findByTenant(Tenant tenant);
    SerieComprobante findByTenantAndTipoComprobanteAndPuntoEmisionAndNumeroSerie(
            Tenant tenant, 
            SerieComprobante.TipoComprobante tipo, 
            Integer puntoEmision, 
            String numeroSerie);
}
