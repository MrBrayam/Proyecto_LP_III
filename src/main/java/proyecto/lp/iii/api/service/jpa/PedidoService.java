package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.repository.PedidoRepository;
import proyecto.lp.iii.api.service.IPedidoService;

@Service
public class PedidoService implements IPedidoService {
    @Autowired
    private PedidoRepository repoPedido;

    public List<Pedido> buscarTodos() { 
        return repoPedido.findAll(); 
    }
    public void guardar(Pedido pedido) { 
        repoPedido.save(pedido); 
    }
    public void modificar(Pedido pedido) { 
        repoPedido.save(pedido); 
    }
    public Optional<Pedido> buscarId(Integer id) { 
        return repoPedido.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPedido.deleteById(id); 
    }
}
