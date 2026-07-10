package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetalleVenta;

public interface IDetalleVentaService {
    List<DetalleVenta> buscarTodos();

    void guardar(DetalleVenta detalleventa);

    void modificar(DetalleVenta detalleventa);

    Optional<DetalleVenta> buscarId(Integer id);

    void eliminar(Integer id);
}
