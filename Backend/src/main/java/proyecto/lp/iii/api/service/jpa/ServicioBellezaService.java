package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.repository.ServicioBellezaRepository;
import proyecto.lp.iii.api.service.IServicioBellezaService;

@Service
public class ServicioBellezaService implements IServicioBellezaService {
    @Autowired
    private ServicioBellezaRepository repoServicioBelleza;
    
    @Autowired
    private EntityManager entityManager;

    public List<ServicioBelleza> buscarTodos() {
        return repoServicioBelleza.findAll();
    }

    public void guardar(ServicioBelleza servicioBelleza) {
        repoServicioBelleza.save(servicioBelleza);
    }

    @Transactional
    public void modificar(ServicioBelleza servicioBelleza) {
        entityManager.merge(servicioBelleza);
    }

    public Optional<ServicioBelleza> buscarId(Integer id) {
        return repoServicioBelleza.findById(id);
    }

    public void eliminar(Integer id) {
        repoServicioBelleza.deleteById(id);
    }
}
