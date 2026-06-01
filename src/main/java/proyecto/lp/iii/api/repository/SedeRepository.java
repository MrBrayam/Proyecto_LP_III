package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    @Query("SELECT s FROM Sede s WHERE s.id_tenants = :tenant")
    List<Sede> findByTenant(@Param("tenant") Tenants tenant);

}
