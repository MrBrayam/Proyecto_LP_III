package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Reclamo;

public interface IReclamoService {
    List<Reclamo> buscarTodos();

    void guardar(Reclamo reclamo);

    void modificar(Reclamo reclamo);

    Optional<Reclamo> buscarId(Integer id);

    void eliminar(Integer id);
}
