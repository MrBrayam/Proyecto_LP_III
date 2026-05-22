package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.CategoriaProducto;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    List<CategoriaProducto> findByTenant(Tenant tenant);
    List<CategoriaProducto> findByTenantAndEstado(Tenant tenant, CategoriaProducto.EstadoCategoria estado);
}
