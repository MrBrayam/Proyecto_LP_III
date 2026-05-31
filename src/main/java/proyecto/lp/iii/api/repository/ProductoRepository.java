package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByTenant(Tenants tenant);

    List<Producto> findByCategoria(CategoriaProducto categoria);

    Optional<Producto> findByCodigoBarras(String codigoBarras);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
