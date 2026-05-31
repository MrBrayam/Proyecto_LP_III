package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DevolucionProveedor;

public interface IDevolucionProveedorService {
    List<DevolucionProveedor> buscarTodos();

    void guardar(DevolucionProveedor devolucionProveedor);

    void modificar(DevolucionProveedor devolucionProveedor);

    Optional<DevolucionProveedor> buscarId(Integer id);

    void eliminar(Integer id);
}
