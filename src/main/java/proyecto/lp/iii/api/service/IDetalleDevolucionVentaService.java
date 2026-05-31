package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;

public interface IDetalleDevolucionVentaService {
    List<DetalleDevolucionVenta> buscarTodos();

    void guardar(DetalleDevolucionVenta detalleDevolucionVenta);

    void modificar(DetalleDevolucionVenta detalleDevolucionVenta);

    Optional<DetalleDevolucionVenta> buscarId(Integer id);

    void eliminar(Integer id);
}
