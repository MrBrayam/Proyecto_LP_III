package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DevolucionVenta;

public interface IDevolucionVentaService {
    List<DevolucionVenta> buscarTodos();

    void guardar(DevolucionVenta devolucionventa);

    void modificar(DevolucionVenta devolucionventa);

    Optional<DevolucionVenta> buscarId(Integer id);

    void eliminar(Integer id);
}
