package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SerieComprobante;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SerieComprobanteRepository extends JpaRepository<SerieComprobante, Integer> {
    List<SerieComprobante> findByTenant(Tenants tenant);

    SerieComprobante findByTenantAndTipoComprobanteAndPuntoEmisionAndNumeroSerie(
            Tenants tenant,

            Integer puntoEmision,
            String numeroSerie);
}
