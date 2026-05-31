package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Sede;

public interface ISedeService {
    List<Sede> buscarTodos();
    void guardar(Sede entity);
    void modificar(Sede entity);
    Optional<Sede> buscarId(Integer id);
    void eliminar(Integer id);
}
