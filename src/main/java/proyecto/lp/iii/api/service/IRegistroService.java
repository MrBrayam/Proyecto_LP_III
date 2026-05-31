package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Registro;

public interface IRegistroService {
    List<Registro> buscarTodos();

    void guardar(Registro r);

    void modificar(Registro r);

    Optional<Registro> buscarId(Integer id);

    void eliminar(Integer id);
}
