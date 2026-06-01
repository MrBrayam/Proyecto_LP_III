package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    @Query("SELECT m FROM Marca m WHERE m.id_tenants = :tenant")
    List<Marca> findByTenant(@Param("tenant") Tenants tenant);
}
