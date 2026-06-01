package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query("SELECT p FROM Producto p WHERE p.id_tenants = :tenant")
    List<Producto> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT p FROM Producto p WHERE p.id_categorias_productos = :categoria")
    List<Producto> findByCategoria(@Param("categoria") CategoriaProducto categoria);

    @Query("SELECT p FROM Producto p WHERE p.codigo_barras = :codigoBarras")
    Optional<Producto> findByCodigoBarras(@Param("codigoBarras") String codigoBarras);

    @Query("SELECT p FROM Producto p WHERE LOWER(p.nombre_producto) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Producto> findByNombreContaining(@Param("nombre") String nombre);
}
