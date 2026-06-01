package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SerieComprobante;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SerieComprobanteRepository extends JpaRepository<SerieComprobante, Integer> {
    @Query("SELECT s FROM SerieComprobante s WHERE s.id_tenants = :tenant")
    List<SerieComprobante> findByTenant(@Param("tenant") Tenants tenant);
        @Query("SELECT s FROM SerieComprobante s WHERE s.id_tenants = :tenant AND s.tipo_comprobante = :tipoComprobante AND s.punto_emision = :puntoEmision AND s.numero_serie = :numeroSerie")
        SerieComprobante findByTenantAndTipoComprobanteAndPuntoEmisionAndNumeroSerie(
            @Param("tenant") Tenants tenant,
            @Param("tipoComprobante") String tipoComprobante,
            @Param("puntoEmision") Integer puntoEmision,
            @Param("numeroSerie") String numeroSerie);
}
