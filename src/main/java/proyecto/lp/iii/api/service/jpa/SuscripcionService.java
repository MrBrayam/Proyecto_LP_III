package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.repository.SuscripcionRepository;
import proyecto.lp.iii.api.service.ISuscripcionService;

@Service
public class SuscripcionService implements ISuscripcionService {
    @Autowired
    private SuscripcionRepository repoSuscripcion;

    public List<Suscripcion> buscarTodos() { 
        return repoSuscripcion.findAll(); 
    }
    public void guardar(Suscripcion suscripcion) { 
        repoSuscripcion.save(suscripcion); 
    }
    public void modificar(Suscripcion suscripcion) { 
        repoSuscripcion.save(suscripcion); 
    }
    public Optional<Suscripcion> buscarId(Integer id) {
         return repoSuscripcion.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoSuscripcion.deleteById(id); 
    }
}
