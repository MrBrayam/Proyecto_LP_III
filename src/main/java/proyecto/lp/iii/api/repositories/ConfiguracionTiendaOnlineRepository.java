package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ConfiguracionTiendaOnline;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionTiendaOnlineRepository extends JpaRepository<ConfiguracionTiendaOnline, Integer> {
    ConfiguracionTiendaOnline findByTenant(Tenant tenant);
}
