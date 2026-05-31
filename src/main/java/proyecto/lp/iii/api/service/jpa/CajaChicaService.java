package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.CajaChica;
import proyecto.lp.iii.api.repository.CajaChicaRepository;
import proyecto.lp.iii.api.service.ICajaChicaService;

@Service
public class CajaChicaService implements ICajaChicaService {
    @Autowired
    private CajaChicaRepository repoCajaChica;

    public List<CajaChica> buscarTodos() {
        return repoCajaChica.findAll();
    }

    public void guardar(CajaChica cajachica) {
        repoCajaChica.save(cajachica);
    }

    public void modificar(CajaChica cajachica) {
        repoCajaChica.save(cajachica);
    }

    public Optional<CajaChica> buscarId(Integer id) {
        return repoCajaChica.findById(id);
    }

    public void eliminar(Integer id) {
        repoCajaChica.deleteById(id);
    }
}
