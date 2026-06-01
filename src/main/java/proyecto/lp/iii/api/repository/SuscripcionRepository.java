package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
    @Query("SELECT s FROM Suscripcion s WHERE s.id_tenants = :tenant")
    List<Suscripcion> findByTenant(@Param("tenant") Tenants tenant);
}
