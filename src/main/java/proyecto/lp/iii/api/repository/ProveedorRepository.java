package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Proveedor;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findByTenant(Tenants tenant);
    List<Proveedor> findByTenantAndEstado(Tenants tenant, Proveedor.EstadoProveedor estado);
}
