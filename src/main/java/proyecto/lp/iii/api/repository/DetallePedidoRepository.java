package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido(Pedido pedido);
}
