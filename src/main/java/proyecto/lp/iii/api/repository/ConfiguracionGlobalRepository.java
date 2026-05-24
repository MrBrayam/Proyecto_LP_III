package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiguracionGlobalRepository extends JpaRepository<ConfiguracionGlobal, Integer> {
    List<ConfiguracionGlobal> findByTenant(Tenant tenant);
    Optional<ConfiguracionGlobal> findByTenantAndClave(Tenant tenant, String clave);
}

