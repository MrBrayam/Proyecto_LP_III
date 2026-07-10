package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.repository.PedidoRepository;
import proyecto.lp.iii.api.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService {
    @Autowired
    private PedidoRepository repoPedido;
    
    @Autowired
    private EntityManager entityManager;

    public List<Pedido> buscarTodos() { 
        return repoPedido.findAll(); 
    }
    
    public Pedido guardar(Pedido pedido) { 
        long count = repoPedido.count();
        if (pedido.getNumero_pedido() == null || pedido.getNumero_pedido().trim().isEmpty()) {
            pedido.setNumero_pedido("PED-" + String.format("%08d", count + 1));
        }
        
        if (pedido.getFecha_pedido() == null) {
            pedido.setFecha_pedido(LocalDateTime.now());
        }
        
        return repoPedido.save(pedido); 
    }
    
    @Transactional
    public void modificar(Pedido pedido) { 
        Optional<Pedido> originalOpt = repoPedido.findById(pedido.getId_pedidos());
        if (originalOpt.isPresent()) {
            Pedido original = originalOpt.get();
            if (pedido.getNumero_pedido() == null || pedido.getNumero_pedido().trim().isEmpty()) {
                pedido.setNumero_pedido(original.getNumero_pedido());
            }
            if (pedido.getFecha_pedido() == null) {
                pedido.setFecha_pedido(original.getFecha_pedido());
            }
        }
        entityManager.merge(pedido); 
    }
    
    public Optional<Pedido> buscarId(Integer id) { 
        return repoPedido.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPedido.deleteById(id); 
    }
}
