package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DevolucionVenta;

public interface IDevolucionVentaService {
    List<DevolucionVenta> buscarTodos();

    void guardar(DevolucionVenta devolucionVenta);

    void modificar(DevolucionVenta devolucionVenta);

    Optional<DevolucionVenta> buscarId(Integer id);

    void eliminar(Integer id);
}
