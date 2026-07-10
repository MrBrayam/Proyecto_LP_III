package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.repository.CuentaPorPagarRepository;
import proyecto.lp.iii.api.service.ICuentaPorPagarService;

@Service
public class CuentaPorPagarService implements ICuentaPorPagarService {
    @Autowired
    private CuentaPorPagarRepository repoCuentaPorPagar;
    
    @Autowired
    private EntityManager entityManager;

    public List<CuentaPorPagar> buscarTodos() {
        return repoCuentaPorPagar.findAll();
    }

    public CuentaPorPagar guardar(CuentaPorPagar cuentaporpagar) {
        return repoCuentaPorPagar.save(cuentaporpagar);
    }

    @Transactional
    public void modificar(CuentaPorPagar cuentaporpagar) {
        entityManager.merge(cuentaporpagar);
    }

    public Optional<CuentaPorPagar> buscarId(Integer id) {
        return repoCuentaPorPagar.findById(id);
    }

    public void eliminar(Integer id) {
        repoCuentaPorPagar.deleteById(id);
    }
}
