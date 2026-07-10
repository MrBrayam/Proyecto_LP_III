package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.repository.DetallePedidoRepository;
import proyecto.lp.iii.api.service.IDetallePedidoService;

@Service
public class DetallePedidoService implements IDetallePedidoService {
    @Autowired
    private DetallePedidoRepository repoDetallePedido;
    
    @Autowired
    private EntityManager entityManager;

    public List<DetallePedido> buscarTodos() {
        return repoDetallePedido.findAll();
    }

    public DetallePedido guardar(DetallePedido detallepedido) {
        return repoDetallePedido.save(detallepedido);
    }

    @Transactional
    public void modificar(DetallePedido detallepedido) {
        entityManager.merge(detallepedido);
    }

    public Optional<DetallePedido> buscarId(Integer id) {
        return repoDetallePedido.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetallePedido.deleteById(id);
    }
}
