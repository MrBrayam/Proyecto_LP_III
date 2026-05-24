package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    List<CategoriaProducto> findByTenant(Tenant tenant);
    List<CategoriaProducto> findByTenantAndEstado(Tenant tenant, CategoriaProducto.EstadoCategoria estado);
}
