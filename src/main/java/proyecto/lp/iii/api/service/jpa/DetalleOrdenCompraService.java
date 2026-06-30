package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.DetalleOrdenCompra;
import proyecto.lp.iii.api.repository.DetalleOrdenCompraRepository;
import proyecto.lp.iii.api.service.IDetalleOrdenCompraService;

@Service
public class DetalleOrdenCompraService implements IDetalleOrdenCompraService {
    @Autowired
    private DetalleOrdenCompraRepository repoDetalleOrdenCompra;
    
    @Autowired
    private EntityManager entityManager;

    public List<DetalleOrdenCompra> buscarTodos() {
        return repoDetalleOrdenCompra.findAll();
    }

    public void guardar(DetalleOrdenCompra detalleordencompra) {
        repoDetalleOrdenCompra.save(detalleordencompra);
    }

    @Transactional
    public void modificar(DetalleOrdenCompra detalleordencompra) {
        entityManager.merge(detalleordencompra);
    }

    public Optional<DetalleOrdenCompra> buscarId(Integer id) {
        return repoDetalleOrdenCompra.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetalleOrdenCompra.deleteById(id);
    }
}
