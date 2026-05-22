package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Venta;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Sede;
import proyecto.lp.iii.api.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByTenant(Tenant tenant);
    List<Venta> findByTenantAndEstado(Tenant tenant, Venta.EstadoVenta estado);
    List<Venta> findBySede(Sede sede);
    List<Venta> findByCliente(Cliente cliente);
}
