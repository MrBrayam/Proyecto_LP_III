package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.BrandingNegocio;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandingNegocioRepository extends JpaRepository<BrandingNegocio, Integer> {
    @Query("SELECT b FROM BrandingNegocio b WHERE b.id_tenants = :tenant")
    BrandingNegocio findByTenant(@Param("tenant") Tenants tenant);
}
