package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    @Query("SELECT d FROM DetallePedido d WHERE d.id_pedidos = :pedido")
    List<DetallePedido> findByPedido(@Param("pedido") Pedido pedido);
}
