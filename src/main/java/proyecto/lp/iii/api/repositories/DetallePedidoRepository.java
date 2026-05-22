package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.DetallePedido;
import proyecto.lp.iii.api.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido(Pedido pedido);
}
