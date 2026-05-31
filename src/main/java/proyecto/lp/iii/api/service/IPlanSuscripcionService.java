package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.PlanSuscripcion;

public interface IPlanSuscripcionService {
    List<PlanSuscripcion> buscarTodos();
    void guardar(PlanSuscripcion entity);
    void modificar(PlanSuscripcion entity);
    Optional<PlanSuscripcion> buscarId(Integer id);
    void eliminar(Integer id);
}
