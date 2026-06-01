package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.MetodoPago;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    @Query("SELECT m FROM MetodoPago m WHERE m.id_tenants = :tenant")
    List<MetodoPago> findByTenant(@Param("tenant") Tenants tenant);
}
