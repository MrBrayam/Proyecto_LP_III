package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.repository.SedeRepository;
import proyecto.lp.iii.api.service.ISedeService;

@Service
public class SedeService implements ISedeService {
    @Autowired
    private SedeRepository repoSede;

    public List<Sede> buscarTodos() {
        return repoSede.findAll();
    }

    public void guardar(Sede sede) {
        repoSede.save(sede);
    }

    public void modificar(Sede sede) {
        repoSede.save(sede);
    }

    public Optional<Sede> buscarId(Integer id) {
        return repoSede.findById(id);
    }

    public void eliminar(Integer id) {
        repoSede.deleteById(id);
    }
}
