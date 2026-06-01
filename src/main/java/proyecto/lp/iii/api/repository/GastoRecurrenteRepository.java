package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.GastoRecurrente;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GastoRecurrenteRepository extends JpaRepository<GastoRecurrente, Integer> {
    @Query("SELECT g FROM GastoRecurrente g WHERE g.id_tenants = :tenant")
    List<GastoRecurrente> findByTenant(@Param("tenant") Tenants tenant);
}
