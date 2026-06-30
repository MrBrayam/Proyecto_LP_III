package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.ProveedorCategoria;
import proyecto.lp.iii.api.repository.ProveedorCategoriaRepository;
import proyecto.lp.iii.api.service.IProveedorCategoriaService;

@Service
public class ProveedorCategoriaService implements IProveedorCategoriaService {
    @Autowired
    private ProveedorCategoriaRepository repo;
    
    @Autowired
    private EntityManager entityManager;

    public List<ProveedorCategoria> buscarTodos() {
        return repo.findAll();
    }

    public void guardar(ProveedorCategoria p) {
        repo.save(p);
    }

    @Transactional
    public void modificar(ProveedorCategoria p) {
        entityManager.merge(p);
    }

    public Optional<ProveedorCategoria> buscarId(Integer id) {
        return repo.findById(id);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
