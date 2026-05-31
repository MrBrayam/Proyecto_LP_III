package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.ServicioBelleza;

public interface IServicioBellezaService {
    List<ServicioBelleza> buscarTodos();
    void guardar(ServicioBelleza entity);
    void modificar(ServicioBelleza entity);
    Optional<ServicioBelleza> buscarId(Integer id);
    void eliminar(Integer id);
}
