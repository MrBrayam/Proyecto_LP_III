package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ConfiguracionTiendaOnline;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionTiendaOnlineRepository extends JpaRepository<ConfiguracionTiendaOnline, Integer> {
    ConfiguracionTiendaOnline findByTenant(Tenant tenant);
}
