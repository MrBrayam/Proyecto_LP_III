package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.repository.ServicioBellezaRepository;
import proyecto.lp.iii.api.service.IServicioBellezaService;

@Service
public class ServicioBellezaService implements IServicioBellezaService {
    @Autowired
    private ServicioBellezaRepository repoServicioBelleza;

    public List<ServicioBelleza> buscarTodos() {
        return repoServicioBelleza.findAll();
    }

    public void guardar(ServicioBelleza servicioBelleza) {
        repoServicioBelleza.save(servicioBelleza);
    }

    public void modificar(ServicioBelleza servicioBelleza) {
        repoServicioBelleza.save(servicioBelleza);
    }

    public Optional<ServicioBelleza> buscarId(Integer id) {
        return repoServicioBelleza.findById(id);
    }

    public void eliminar(Integer id) {
        repoServicioBelleza.deleteById(id);
    }
}
