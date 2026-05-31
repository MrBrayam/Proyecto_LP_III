package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.OrdenCompra;

public interface IOrdenCompraService {
    List<OrdenCompra> buscarTodos();

    void guardar(OrdenCompra ordencompra);

    void modificar(OrdenCompra ordencompra);

    Optional<OrdenCompra> buscarId(Integer id);

    void eliminar(Integer id);
}
