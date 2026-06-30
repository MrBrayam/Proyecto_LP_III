package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.MetodoPago;
import proyecto.lp.iii.api.repository.MetodoPagoRepository;
import proyecto.lp.iii.api.service.IMetodoPagoService;

@Service
public class MetodoPagoService implements IMetodoPagoService {
    @Autowired
    private MetodoPagoRepository repoMetodoPago;
    
    @Autowired
    private EntityManager entityManager;

    public List<MetodoPago> buscarTodos() {
        return repoMetodoPago.findAll();
    }

    public void guardar(MetodoPago metodoPago) {
        repoMetodoPago.save(metodoPago);
    }

    @Transactional
    public void modificar(MetodoPago metodoPago) {
        entityManager.merge(metodoPago);
    }

    public Optional<MetodoPago> buscarId(Integer id) {
        return repoMetodoPago.findById(id);
    }

    public void eliminar(Integer id) {
        repoMetodoPago.deleteById(id);
    }
}
