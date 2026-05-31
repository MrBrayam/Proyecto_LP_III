package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.PreciosPlan;
import proyecto.lp.iii.api.repository.PreciosPlanRepository;
import proyecto.lp.iii.api.service.IPreciosPlanService;

@Service
public class PreciosPlanService implements IPreciosPlanService {
    @Autowired
    private PreciosPlanRepository repo;

    public List<PreciosPlan> buscarTodos() {
        return repo.findAll();
    }

    public void guardar(PreciosPlan p) {
        repo.save(p);
    }

    public void modificar(PreciosPlan p) {
        repo.save(p);
    }

    public Optional<PreciosPlan> buscarId(Integer id) {
        return repo.findById(id);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
