package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoOperativo;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoOperativoRepository extends JpaRepository<GastoOperativo, Integer> {
    @Query("SELECT g FROM GastoOperativo g WHERE g.id_tenants = :tenant")
    List<GastoOperativo> findByTenant(@Param("tenant") Tenants tenant);
}
