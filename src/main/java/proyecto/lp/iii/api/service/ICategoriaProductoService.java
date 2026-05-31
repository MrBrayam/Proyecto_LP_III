package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.CategoriaProducto;

public interface ICategoriaProductoService {
    List<CategoriaProducto> buscarTodos();

    void guardar(CategoriaProducto categoriaproducto);

    void modificar(CategoriaProducto categoriaproducto);

    Optional<CategoriaProducto> buscarId(Integer id);

    void eliminar(Integer id);
}
