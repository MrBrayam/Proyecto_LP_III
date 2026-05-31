package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.MovimientoInventario;

public interface IMovimientoInventarioService {
    List<MovimientoInventario> buscarTodos();

    void guardar(MovimientoInventario movimientoinventario);

    void modificar(MovimientoInventario movimientoinventario);

    Optional<MovimientoInventario> buscarId(Integer id);

    void eliminar(Integer id);
}
