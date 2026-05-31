package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.RolPersonalizado;

public interface IRolPersonalizadoService {
    List<RolPersonalizado> buscarTodos();
    void guardar(RolPersonalizado entity);
    void modificar(RolPersonalizado entity);
    Optional<RolPersonalizado> buscarId(Integer id);
    void eliminar(Integer id);
}
