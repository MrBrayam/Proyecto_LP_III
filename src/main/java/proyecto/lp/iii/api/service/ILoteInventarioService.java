package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.LoteInventario;

public interface ILoteInventarioService {
    List<LoteInventario> buscarTodos();

    void guardar(LoteInventario loteinventario);

    void modificar(LoteInventario loteinventario);

    Optional<LoteInventario> buscarId(Integer id);

    void eliminar(Integer id);
}
