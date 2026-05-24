package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.entity.Tenant;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByTenant(Tenant tenant);
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByEstado(Pedido.EstadoPedido estado);
    List<Pedido> findByTenantAndEstado(Tenant tenant, Pedido.EstadoPedido estado);
}
