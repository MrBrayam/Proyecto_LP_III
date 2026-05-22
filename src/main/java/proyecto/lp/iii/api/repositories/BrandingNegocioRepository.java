package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.BrandingNegocio;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandingNegocioRepository extends JpaRepository<BrandingNegocio, Integer> {
    BrandingNegocio findByTenant(Tenant tenant);
}
