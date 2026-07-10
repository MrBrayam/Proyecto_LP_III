package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.repository.PromocionRepository;
import proyecto.lp.iii.api.service.IPromocionService;

@Service
public class PromocionService implements IPromocionService {
    @Autowired
    private PromocionRepository repoPromocion;
    
    @Autowired
    private EntityManager entityManager;

    public List<Promocion> buscarTodos() { 
        return repoPromocion.findAll(); 
    }
    public void guardar(Promocion promocion) { 
        repoPromocion.save(promocion); 
    }
    @Transactional
    public void modificar(Promocion promocion) { 
        entityManager.merge(promocion); 
    }
    public Optional<Promocion> buscarId(Integer id) { 
        return repoPromocion.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPromocion.deleteById(id); 
    }
}
