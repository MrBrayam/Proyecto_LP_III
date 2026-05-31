package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetalleOrdenCompra;

public interface IDetalleOrdenCompraService {
    List<DetalleOrdenCompra> buscarTodos();

    void guardar(DetalleOrdenCompra detalleordenCompra);

    void modificar(DetalleOrdenCompra detalleordenCompra);

    Optional<DetalleOrdenCompra> buscarId(Integer id);

    void eliminar(Integer id);
}
