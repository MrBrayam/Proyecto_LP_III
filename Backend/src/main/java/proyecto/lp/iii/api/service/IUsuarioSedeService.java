package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.UsuarioSede;

public interface IUsuarioSedeService {
    List<UsuarioSede> buscarTodos();

    void guardar(UsuarioSede usuariosede);

    void modificar(UsuarioSede usuariosede);

    Optional<UsuarioSede> buscarId(Integer id);

    void eliminar(Integer id);
}
