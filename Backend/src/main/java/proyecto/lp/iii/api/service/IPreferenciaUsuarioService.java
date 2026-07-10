package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.PreferenciaUsuario;

public interface IPreferenciaUsuarioService {
    List<PreferenciaUsuario> buscarTodos();

    void guardar(PreferenciaUsuario preferenciausuario);

    void modificar(PreferenciaUsuario preferenciausuario);

    Optional<PreferenciaUsuario> buscarId(Integer id);

    void eliminar(Integer id);
}
