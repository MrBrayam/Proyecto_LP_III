package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Proveedor;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    @Query("SELECT p FROM Proveedor p WHERE p.id_tenants = :tenant")
    List<Proveedor> findByTenant(@Param("tenant") Tenants tenant);

}
