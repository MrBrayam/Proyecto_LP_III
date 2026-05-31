package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetalleOrdenCompra;

public interface IDetalleOrdenCompraService {
    List<DetalleOrdenCompra> buscarTodos();

    void guardar(DetalleOrdenCompra detalleOrdenCompra);

    void modificar(DetalleOrdenCompra detalleOrdenCompra);

    Optional<DetalleOrdenCompra> buscarId(Integer id);

    void eliminar(Integer id);
}
