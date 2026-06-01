package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Registros;

public interface IRegistrosService {
    List<Registros> buscarTodos();

    void guardar(Registros registro);

    void modificar(Registros registro);

    Optional<Registros> buscarId(Integer id);

    void eliminar(Integer id);
}
