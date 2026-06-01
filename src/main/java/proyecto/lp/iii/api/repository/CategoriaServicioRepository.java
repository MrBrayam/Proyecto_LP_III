package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CategoriaServicio;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaServicioRepository extends JpaRepository<CategoriaServicio, Integer> {
    @Query("SELECT c FROM CategoriaServicio c WHERE c.id_tenants = :tenant")
    List<CategoriaServicio> findByTenant(@Param("tenant") Tenants tenant);
}
