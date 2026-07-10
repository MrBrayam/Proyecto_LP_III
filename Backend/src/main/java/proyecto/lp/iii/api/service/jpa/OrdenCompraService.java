package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.OrdenCompra;
import proyecto.lp.iii.api.repository.OrdenCompraRepository;
import proyecto.lp.iii.api.service.IOrdenCompraService;

@Service
public class OrdenCompraService implements IOrdenCompraService {
    @Autowired
    private OrdenCompraRepository repoOrdenCompra;
    
    @Autowired
    private EntityManager entityManager;

    public List<OrdenCompra> buscarTodos() { 
        return repoOrdenCompra.findAll(); 
    }
    public void guardar(OrdenCompra ordencompra) { 
        repoOrdenCompra.save(ordencompra); 
    }
    @Transactional
    public void modificar(OrdenCompra ordencompra) { 
        entityManager.merge(ordencompra); 
    }
    public Optional<OrdenCompra> buscarId(Integer id) { 
        return repoOrdenCompra.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoOrdenCompra.deleteById(id); 
    }
}
