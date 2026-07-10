package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.ServicioCita;

public interface IServicioCitaService {
    List<ServicioCita> buscarTodos();
    void guardar(ServicioCita entity);
    void modificar(ServicioCita entity);
    Optional<ServicioCita> buscarId(Integer id);
    void eliminar(Integer id);
}
