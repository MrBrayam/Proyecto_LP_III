package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.ImagenProducto;

public interface IImagenProductoService {
    List<ImagenProducto> buscarTodos();

    void guardar(ImagenProducto imagenproducto);

    void modificar(ImagenProducto imagenproducto);

    Optional<ImagenProducto> buscarId(Integer id);

    void eliminar(Integer id);
}
