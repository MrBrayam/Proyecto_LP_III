package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ZonaDelivery;
import proyecto.lp.iii.api.repository.ZonaDeliveryRepository;
import proyecto.lp.iii.api.service.IZonaDeliveryService;

@Service
public class ZonaDeliveryService implements IZonaDeliveryService {
    @Autowired
    private ZonaDeliveryRepository repoZonaDelivery;

    public List<ZonaDelivery> buscarTodos() { 
        return repoZonaDelivery.findAll(); 
    }
    public void guardar(ZonaDelivery zonadelivery) { 
        repoZonaDelivery.save(zonadelivery); 
    }
    public void modificar(ZonaDelivery zonadelivery) { 
        repoZonaDelivery.save(zonadelivery); 
    }
    public Optional<ZonaDelivery> buscarId(Integer id) { 
        return repoZonaDelivery.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoZonaDelivery.deleteById(id); 
    }
}
