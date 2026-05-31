package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Cita;
import proyecto.lp.iii.api.repository.CitaRepository;
import proyecto.lp.iii.api.service.ICitaService;

@Service
public class CitaService implements ICitaService {
    @Autowired
    private CitaRepository repoCita;

    public List<Cita> buscarTodos() {
        return repoCita.findAll();
    }

    public void guardar(Cita cita) {
        repoCita.save(cita);
    }

    public void modificar(Cita cita) {
        repoCita.save(cita);
    }

    public Optional<Cita> buscarId(Integer id) {
        return repoCita.findById(id);
    }

    public void eliminar(Integer id) {
        repoCita.deleteById(id);
    }
}
