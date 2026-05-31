package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetallePedido;

public interface IDetallePedidoService {
    List<DetallePedido> buscarTodos();

    void guardar(DetallePedido detallepedido);

    void modificar(DetallePedido detallepedido);

    Optional<DetallePedido> buscarId(Integer id);

    void eliminar(Integer id);
}
