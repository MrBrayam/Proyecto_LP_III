package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Cita;
import proyecto.lp.iii.api.repository.CitaRepository;
import proyecto.lp.iii.api.service.ICitaService;

@Service
public class CitaService implements ICitaService {
    @Autowired
    private CitaRepository repoCita;
    
    @Autowired
    private EntityManager entityManager;

    public List<Cita> buscarTodos() {
        return repoCita.findAll();
    }

    @Transactional
    public Cita guardar(Cita cita) {
        return repoCita.save(cita);
    }

    @Transactional
    public void modificar(Cita cita) {
        entityManager.merge(cita);
    }

    public Optional<Cita> buscarId(Integer id) {
        return repoCita.findById(id);
    }

    public void eliminar(Integer id) {
        repoCita.deleteById(id);
    }
}
