package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;
import proyecto.lp.iii.api.repository.ConfiguracionGlobalRepository;
import proyecto.lp.iii.api.service.IConfiguracionGlobalService;

@Service
public class ConfiguracionGlobalService implements IConfiguracionGlobalService {
    @Autowired
    private ConfiguracionGlobalRepository repoConfiguracionGlobal;

    public List<ConfiguracionGlobal> buscarTodos() {
        return repoConfiguracionGlobal.findAll();
    }

    public void guardar(ConfiguracionGlobal configuracionglobal) {
        repoConfiguracionGlobal.save(configuracionglobal);
    }

    public void modificar(ConfiguracionGlobal configuracionglobal) {
        repoConfiguracionGlobal.save(configuracionglobal);
    }

    public Optional<ConfiguracionGlobal> buscarId(Integer id) {
        return repoConfiguracionGlobal.findById(id);
    }

    public void eliminar(Integer id) {
        repoConfiguracionGlobal.deleteById(id);
    }
}
