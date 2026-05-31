package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByTenant(Tenants tenant);

    List<Venta> findBySede(Sede sede);

    List<Venta> findByCliente(Cliente cliente);
}
