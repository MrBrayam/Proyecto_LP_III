package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
    @Query("SELECT p FROM Promocion p WHERE p.id_tenants = :tenant")
    List<Promocion> findByTenant(@Param("tenant") Tenants tenant);

}
