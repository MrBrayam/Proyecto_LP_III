package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.repository.MovimientoInventarioRepository;
import proyecto.lp.iii.api.service.IMovimientoInventarioService;

@Service
public class MovimientoInventarioService implements IMovimientoInventarioService {
    @Autowired
    private MovimientoInventarioRepository repoMovimientoInventario;
    
    @Autowired
    private EntityManager entityManager;

    public List<MovimientoInventario> buscarTodos() { 
        return repoMovimientoInventario.findAll(); 
    }
    public void guardar(MovimientoInventario movimientoinventario) { 
        repoMovimientoInventario.save(movimientoinventario); 
    }
    @Transactional
    public void modificar(MovimientoInventario movimientoinventario) { 
        entityManager.merge(movimientoinventario); 
    }
    public Optional<MovimientoInventario> buscarId(Integer id) { 
        return repoMovimientoInventario.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoMovimientoInventario.deleteById(id); 
    }
}
