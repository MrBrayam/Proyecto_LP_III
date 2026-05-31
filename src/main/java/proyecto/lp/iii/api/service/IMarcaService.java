package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Marca;

public interface IMarcaService {
    List<Marca> buscarTodos();

    void guardar(Marca marca);

    void modificar(Marca marca);

    Optional<Marca> buscarId(Integer id);

    void eliminar(Integer id);
}
