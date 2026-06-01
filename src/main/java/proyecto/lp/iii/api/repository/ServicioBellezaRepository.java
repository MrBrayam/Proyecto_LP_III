package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioBellezaRepository extends JpaRepository<ServicioBelleza, Integer> {
    @Query("SELECT s FROM ServicioBelleza s WHERE s.id_tenants = :tenant")
    List<ServicioBelleza> findByTenant(@Param("tenant") Tenants tenant);
}
