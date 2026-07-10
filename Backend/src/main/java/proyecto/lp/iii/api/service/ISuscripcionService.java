package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Suscripcion;

public interface ISuscripcionService {
    List<Suscripcion> buscarTodos();

    void guardar(Suscripcion suscripcion);

    void modificar(Suscripcion suscripcion);

    Optional<Suscripcion> buscarId(Integer id);

    void eliminar(Integer id);
}
