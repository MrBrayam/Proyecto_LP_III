package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.MetodoPago;
import proyecto.lp.iii.api.repository.MetodoPagoRepository;
import proyecto.lp.iii.api.service.IMetodoPagoService;

@Service
public class MetodoPagoService implements IMetodoPagoService {
    @Autowired
    private MetodoPagoRepository repoMetodoPago;

    public List<MetodoPago> buscarTodos() {
        return repoMetodoPago.findAll();
    }

    public void guardar(MetodoPago metodoPago) {
        repoMetodoPago.save(metodoPago);
    }

    public void modificar(MetodoPago metodoPago) {
        repoMetodoPago.save(metodoPago);
    }

    public Optional<MetodoPago> buscarId(Integer id) {
        return repoMetodoPago.findById(id);
    }

    public void eliminar(Integer id) {
        repoMetodoPago.deleteById(id);
    }
}
