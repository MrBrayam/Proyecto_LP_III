package proyecto.lp.iii.api.repository;

import java.util.Optional;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantsRepository extends JpaRepository<Tenants, Integer> {
    @Query("SELECT t FROM Tenants t WHERE t.id_tenants = :id")
    Optional<Tenants> findById(@Param("id") Integer id);
}
