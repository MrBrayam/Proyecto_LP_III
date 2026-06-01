package proyecto.lp.iii.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proyecto.lp.iii.api.entity.Proveedor;
import proyecto.lp.iii.api.entity.ProveedorCategoria;

@Repository
public interface ProveedorCategoriaRepository extends JpaRepository<ProveedorCategoria, Integer> {
    @Query("SELECT p FROM ProveedorCategoria p WHERE p.id_proveedores = :proveedor ORDER BY p.id_proveedor_categorias DESC")
    List<ProveedorCategoria> findByProveedoresAndVigenteTrueOrderByFechaFinDesc(@Param("proveedor") Proveedor proveedor);

    @Query("SELECT p FROM ProveedorCategoria p WHERE p.id_proveedor_categorias = :id")
    Optional<ProveedorCategoria> findById(@Param("id") Integer id);
}
