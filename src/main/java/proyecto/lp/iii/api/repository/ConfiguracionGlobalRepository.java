package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiguracionGlobalRepository extends JpaRepository<ConfiguracionGlobal, Integer> {
    List<ConfiguracionGlobal> findByTenant(Tenants tenant);
    Optional<ConfiguracionGlobal> findByTenantAndClave(Tenants tenant, String clave);
}

