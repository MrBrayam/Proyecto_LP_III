package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Notificacion;

public interface INotificacionService {
    List<Notificacion> buscarTodos();

    void guardar(Notificacion notificacion);

    void modificar(Notificacion notificacion);

    Optional<Notificacion> buscarId(Integer id);

    void eliminar(Integer id);
}
