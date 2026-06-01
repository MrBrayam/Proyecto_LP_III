package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    @Query("SELECT c FROM CategoriaProducto c WHERE c.id_tenants = :tenant")
    List<CategoriaProducto> findByTenant(@Param("tenant") Tenants tenant);
}
