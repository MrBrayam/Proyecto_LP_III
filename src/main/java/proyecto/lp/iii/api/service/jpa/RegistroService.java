package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Registro;
import proyecto.lp.iii.api.repository.RegistroRepository;
import proyecto.lp.iii.api.service.IRegistroService;

@Service
public class RegistroService implements IRegistroService {
    @Autowired
    private RegistroRepository repo;

    public List<Registro> buscarTodos() {
        return repo.findAll();
    }

    public void guardar(Registro r) {
        repo.save(r);
    }

    public void modificar(Registro r) {
        repo.save(r);
    }

    public Optional<Registro> buscarId(Integer id) {
        return repo.findById(id);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
