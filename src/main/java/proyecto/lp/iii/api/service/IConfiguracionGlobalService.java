package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;

public interface IConfiguracionGlobalService {
    List<ConfiguracionGlobal> buscarTodos();

    void guardar(ConfiguracionGlobal configuracionGlobal);

    void modificar(ConfiguracionGlobal configuracionGlobal);

    Optional<ConfiguracionGlobal> buscarId(Integer id);

    void eliminar(Integer id);
}
