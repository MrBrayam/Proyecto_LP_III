package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Reclamo;
import proyecto.lp.iii.api.repository.ReclamoRepository;
import proyecto.lp.iii.api.service.IReclamoService;

@Service
public class ReclamoService implements IReclamoService {
    @Autowired
    private ReclamoRepository repoReclamo;
    
    @Autowired
    private EntityManager entityManager;

    public List<Reclamo> buscarTodos() { 
        return repoReclamo.findAll(); 
    }
    public void guardar(Reclamo reclamo) { 
        repoReclamo.save(reclamo); 
    }
    @Transactional
    public void modificar(Reclamo reclamo) { 
        entityManager.merge(reclamo); 
    }
    public Optional<Reclamo> buscarId(Integer id) { 
        return repoReclamo.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoReclamo.deleteById(id); 
    }
}
