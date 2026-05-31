package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.PlanSuscripcion;
import proyecto.lp.iii.api.repository.PlanSuscripcionRepository;
import proyecto.lp.iii.api.service.IPlanSuscripcionService;

@Service
public class PlanSuscripcionService implements IPlanSuscripcionService {
    @Autowired
    private PlanSuscripcionRepository repoPlanSuscripcion;

    public List<PlanSuscripcion> buscarTodos() {
        return repoPlanSuscripcion.findAll();
    }

    public void guardar(PlanSuscripcion planSuscripcion) {
        repoPlanSuscripcion.save(planSuscripcion);
    }

    public void modificar(PlanSuscripcion planSuscripcion) {
        repoPlanSuscripcion.save(planSuscripcion);
    }

    public Optional<PlanSuscripcion> buscarId(Integer id) {
        return repoPlanSuscripcion.findById(id);
    }

    public void eliminar(Integer id) {
        repoPlanSuscripcion.deleteById(id);
    }
}
