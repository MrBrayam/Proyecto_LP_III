package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.ProveedorCategoria;

public interface IProveedorCategoriaService {
    List<ProveedorCategoria> buscarTodos();

    void guardar(ProveedorCategoria p);

    void modificar(ProveedorCategoria p);

    Optional<ProveedorCategoria> buscarId(Integer id);

    void eliminar(Integer id);
}
