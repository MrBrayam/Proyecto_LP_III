package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ConfiguracionTiendaOnline;

public interface IConfiguracionTiendaOnlineService {
    List<ConfiguracionTiendaOnline> buscarTodos();

    void guardar(ConfiguracionTiendaOnline configuracionTiendaOnline);

    void modificar(ConfiguracionTiendaOnline configuracionTiendaOnline);

    Optional<ConfiguracionTiendaOnline> buscarId(Integer id);

    void eliminar(Integer id);
}
