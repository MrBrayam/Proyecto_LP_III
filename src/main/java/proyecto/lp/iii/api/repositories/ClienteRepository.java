package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Cliente;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByTenant(Tenant tenant);
    List<Cliente> findByTenantAndEstado(Tenant tenant, Cliente.EstadoCliente estado);
    Cliente findByNumeroDocumento(String numeroDocumento);
}
