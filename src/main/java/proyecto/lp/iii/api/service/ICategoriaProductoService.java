package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.CategoriaProducto;

public interface ICategoriaProductoService {
    List<CategoriaProducto> buscarTodos();

    void guardar(CategoriaProducto categoriaProducto);

    void modificar(CategoriaProducto categoriaProducto);

    Optional<CategoriaProducto> buscarId(Integer id);

    void eliminar(Integer id);
}
