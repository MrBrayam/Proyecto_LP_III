package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.BrandingNegocio;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandingNegocioRepository extends JpaRepository<BrandingNegocio, Integer> {
    BrandingNegocio findByTenant(Tenant tenant);
}
