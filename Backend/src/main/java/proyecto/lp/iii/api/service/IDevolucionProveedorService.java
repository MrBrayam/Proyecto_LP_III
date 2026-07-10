package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DevolucionProveedor;

public interface IDevolucionProveedorService {
    List<DevolucionProveedor> buscarTodos();

    void guardar(DevolucionProveedor devolucionproveedor);

    void modificar(DevolucionProveedor devolucionproveedor);

    Optional<DevolucionProveedor> buscarId(Integer id);

    void eliminar(Integer id);
}
