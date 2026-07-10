package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Proveedor;

public interface IProveedorService {
    List<Proveedor> buscarTodos();

    void guardar(Proveedor proveedor);

    void modificar(Proveedor proveedor);

    Optional<Proveedor> buscarId(Integer id);

    void eliminar(Integer id);
}
