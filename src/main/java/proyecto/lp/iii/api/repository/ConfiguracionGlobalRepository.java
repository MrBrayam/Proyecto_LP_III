package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConfiguracionGlobalRepository extends JpaRepository<ConfiguracionGlobal, Integer> {
    @Query("SELECT c FROM ConfiguracionGlobal c WHERE c.id_tenants = :tenant")
    List<ConfiguracionGlobal> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT c FROM ConfiguracionGlobal c WHERE c.id_tenants = :tenant AND c.clave = :clave")
    Optional<ConfiguracionGlobal> findByTenantAndClave(@Param("tenant") Tenants tenant, @Param("clave") String clave);
}

