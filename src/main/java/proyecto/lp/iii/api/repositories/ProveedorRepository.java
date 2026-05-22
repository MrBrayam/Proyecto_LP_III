package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Proveedor;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    List<Proveedor> findByTenant(Tenant tenant);
    List<Proveedor> findByTenantAndEstado(Tenant tenant, Proveedor.EstadoProveedor estado);
}
