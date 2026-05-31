package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.PermisoRol;

public interface IPermisoRolService {
    List<PermisoRol> buscarTodos();

    void guardar(PermisoRol entity);

    void modificar(PermisoRol entity);

    Optional<PermisoRol> buscarId(Integer id);
    
    void eliminar(Integer id);
}
