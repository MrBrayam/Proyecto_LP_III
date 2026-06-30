package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.repository.PagoProveedorRepository;
import proyecto.lp.iii.api.service.IPagoProveedorService;

@Service
public class PagoProveedorService implements IPagoProveedorService {
    @Autowired
    private PagoProveedorRepository repoPagoProveedor;
    
    @Autowired
    private EntityManager entityManager;

    public List<PagoProveedor> buscarTodos() { 
        return repoPagoProveedor.findAll(); 
    }
    public void guardar(PagoProveedor pagoproveedor) { 
        repoPagoProveedor.save(pagoproveedor); 
    }
    @Transactional
    public void modificar(PagoProveedor pagoproveedor) { 
        entityManager.merge(pagoproveedor); 
    }
    public Optional<PagoProveedor> buscarId(Integer id) { 
        return repoPagoProveedor.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPagoProveedor.deleteById(id); 
    }
}
