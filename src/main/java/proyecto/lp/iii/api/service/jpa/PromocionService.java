package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.repository.PromocionRepository;
import proyecto.lp.iii.api.service.IPromocionService;

@Service
public class PromocionService implements IPromocionService {
    @Autowired
    private PromocionRepository repoPromocion;

    public List<Promocion> buscarTodos() { 
        return repoPromocion.findAll(); 
    }
    public void guardar(Promocion promocion) { 
        repoPromocion.save(promocion); 
    }
    public void modificar(Promocion promocion) { 
        repoPromocion.save(promocion); 
    }
    public Optional<Promocion> buscarId(Integer id) { 
        return repoPromocion.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPromocion.deleteById(id); 
    }
}
