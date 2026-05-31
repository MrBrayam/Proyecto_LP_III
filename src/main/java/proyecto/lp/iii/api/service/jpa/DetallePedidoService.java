package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.repository.DetallePedidoRepository;
import proyecto.lp.iii.api.service.IDetallePedidoService;

@Service
public class DetallePedidoService implements IDetallePedidoService {
    @Autowired
    private DetallePedidoRepository repoDetallePedido;

    public List<DetallePedido> buscarTodos() {
        return repoDetallePedido.findAll();
    }

    public void guardar(DetallePedido detallepedido) {
        repoDetallePedido.save(detallepedido);
    }

    public void modificar(DetallePedido detallepedido) {
        repoDetallePedido.save(detallepedido);
    }

    public Optional<DetallePedido> buscarId(Integer id) {
        return repoDetallePedido.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetallePedido.deleteById(id);
    }
}
