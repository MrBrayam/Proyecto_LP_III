package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ConfiguracionTiendaOnline;
import proyecto.lp.iii.api.repository.ConfiguracionTiendaOnlineRepository;
import proyecto.lp.iii.api.service.IConfiguracionTiendaOnlineService;

@Service
public class ConfiguracionTiendaOnlineService implements IConfiguracionTiendaOnlineService {
    @Autowired
    private ConfiguracionTiendaOnlineRepository repoConfiguracionTiendaOnline;

    public List<ConfiguracionTiendaOnline> buscarTodos() {
        return repoConfiguracionTiendaOnline.findAll();
    }

    public void guardar(ConfiguracionTiendaOnline configuraciontiendaonline) {
        repoConfiguracionTiendaOnline.save(configuraciontiendaonline);
    }

    public void modificar(ConfiguracionTiendaOnline configuraciontiendaonline) {
        repoConfiguracionTiendaOnline.save(configuraciontiendaonline);
    }

    public Optional<ConfiguracionTiendaOnline> buscarId(Integer id) {
        return repoConfiguracionTiendaOnline.findById(id);
    }

    public void eliminar(Integer id) {
        repoConfiguracionTiendaOnline.deleteById(id);
    }
}
