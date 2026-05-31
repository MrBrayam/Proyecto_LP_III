package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.FacturaSuscripcion;
import proyecto.lp.iii.api.repository.FacturaSuscripcionRepository;
import proyecto.lp.iii.api.service.IFacturaSuscripcionService;

@Service
public class FacturaSuscripcionService implements IFacturaSuscripcionService {
    @Autowired
    private FacturaSuscripcionRepository repoFacturaSuscripcion;

    public List<FacturaSuscripcion> buscarTodos() { 
        return repoFacturaSuscripcion.findAll(); 
    }
    public void guardar(FacturaSuscripcion facturasuscripcion) { 
        repoFacturaSuscripcion.save(facturasuscripcion);
    }
    public void modificar(FacturaSuscripcion facturasuscripcion) { 
        repoFacturaSuscripcion.save(facturasuscripcion); 
    }
    public Optional<FacturaSuscripcion> buscarId(Integer id) {
         return repoFacturaSuscripcion.findById(id); 
        }
    public void eliminar(Integer id) { 
        repoFacturaSuscripcion.deleteById(id); 
    }
}
