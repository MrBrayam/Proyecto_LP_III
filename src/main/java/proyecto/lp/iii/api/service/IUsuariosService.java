package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Usuarios;

public interface IUsuariosService {
    List<Usuarios> buscarTodos();

    void guardar(Usuarios usuario);

    void modificar(Usuarios usuario);

    Optional<Usuarios> buscarId(Integer id);

    void eliminar(Integer id);
    
}
