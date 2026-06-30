package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Repartidor;
import proyecto.lp.iii.api.repository.RepartidorRepository;
import proyecto.lp.iii.api.service.IRepartidorService;

@Service
public class RepartidorService implements IRepartidorService {
    @Autowired
    private RepartidorRepository repoRepartidor;
    
    @Autowired
    private EntityManager entityManager;

    public List<Repartidor> buscarTodos() { 
        return repoRepartidor.findAll();
    }
    public void guardar(Repartidor repartidor) {
         repoRepartidor.save(repartidor); 
    }
    @Transactional
    public void modificar(Repartidor repartidor) { 
        entityManager.merge(repartidor); 
    }
    public Optional<Repartidor> buscarId(Integer id) { 
        return repoRepartidor.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoRepartidor.deleteById(id); 
    }
}
