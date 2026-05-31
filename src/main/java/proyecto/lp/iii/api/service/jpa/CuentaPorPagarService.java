package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.repository.CuentaPorPagarRepository;
import proyecto.lp.iii.api.service.ICuentaPorPagarService;

@Service
public class CuentaPorPagarService implements ICuentaPorPagarService {
    @Autowired
    private CuentaPorPagarRepository repoCuentaPorPagar;

    public List<CuentaPorPagar> buscarTodos() {
        return repoCuentaPorPagar.findAll();
    }

    public void guardar(CuentaPorPagar cuentaporpagar) {
        repoCuentaPorPagar.save(cuentaporpagar);
    }

    public void modificar(CuentaPorPagar cuentaporpagar) {
        repoCuentaPorPagar.save(cuentaporpagar);
    }

    public Optional<CuentaPorPagar> buscarId(Integer id) {
        return repoCuentaPorPagar.findById(id);
    }

    public void eliminar(Integer id) {
        repoCuentaPorPagar.deleteById(id);
    }
}
