package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Registros;
import proyecto.lp.iii.api.repository.RegistrosRepository;
import proyecto.lp.iii.api.service.IRegistrosService;

@Service
public class RegistrosService implements IRegistrosService {
    @Autowired
    private RegistrosRepository repo;
    
    @Autowired
    private EntityManager entityManager;

    public List<Registros> buscarTodos() {
        return repo.findAll();
    }

    public void guardar(Registros registro) {
        repo.save(registro);
    }

    @Transactional
    public void modificar(Registros registro) {
        entityManager.merge(registro);
    }

    public Optional<Registros> buscarId(Integer id) {
        return repo.findById(id);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
