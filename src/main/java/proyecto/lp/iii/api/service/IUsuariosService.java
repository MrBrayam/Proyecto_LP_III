package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Usuario;

public interface IUsuariosService {
    List<Usuario> buscarTodos();

    void guardar(Usuario usuario);

    void modificar(Usuario usuario);

    Optional<Usuario> buscarId(Integer id);

    void eliminar(Integer id);
    
}
