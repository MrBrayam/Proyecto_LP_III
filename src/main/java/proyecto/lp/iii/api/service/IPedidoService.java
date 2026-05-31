package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Pedido;

public interface IPedidoService {
    List<Pedido> buscarTodos();

    void guardar(Pedido pedido);

    void modificar(Pedido pedido);

    Optional<Pedido> buscarId(Integer id);

    void eliminar(Integer id);
}
