package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Producto;

public interface IProductoService {
    List<Producto> buscarTodos();

    void guardar(Producto producto);

    void modificar(Producto producto);

    Optional<Producto> buscarId(Integer id);

    void eliminar(Integer id);
}
