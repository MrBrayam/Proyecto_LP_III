package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Reclamo;
import proyecto.lp.iii.api.repository.ReclamoRepository;
import proyecto.lp.iii.api.service.IReclamoService;

@Service
public class ReclamoService implements IReclamoService {
    @Autowired
    private ReclamoRepository repoReclamo;

    public List<Reclamo> buscarTodos() { 
        return repoReclamo.findAll(); 
    }
    public void guardar(Reclamo reclamo) { 
        repoReclamo.save(reclamo); 
    }
    public void modificar(Reclamo reclamo) { 
        repoReclamo.save(reclamo); 
    }
    public Optional<Reclamo> buscarId(Integer id) { 
        return repoReclamo.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoReclamo.deleteById(id); 
    }
}
