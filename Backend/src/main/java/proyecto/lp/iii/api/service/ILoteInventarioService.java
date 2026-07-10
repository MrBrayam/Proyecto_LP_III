package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.Producto;

public interface ILoteInventarioService {
    List<LoteInventario> buscarTodos();

    LoteInventario guardar(LoteInventario loteinventario);

    void modificar(LoteInventario loteinventario);

    Optional<LoteInventario> buscarId(Integer id);

    void eliminar(Integer id);

    List<LoteInventario> buscarPorProductoDisponible(Producto producto);

    List<LoteInventario> buscarPorProducto(Producto producto);
}
