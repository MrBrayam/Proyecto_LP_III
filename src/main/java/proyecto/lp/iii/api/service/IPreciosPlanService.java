package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.PreciosPlan;

public interface IPreciosPlanService {
    List<PreciosPlan> buscarTodos();

    void guardar(PreciosPlan p);

    void modificar(PreciosPlan p);

    Optional<PreciosPlan> buscarId(Integer id);

    void eliminar(Integer id);
}
