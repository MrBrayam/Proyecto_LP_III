package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.HorarioOperacion;

public interface IHorarioOperacionService {
    List<HorarioOperacion> buscarTodos();

    void guardar(HorarioOperacion horariooperacion);

    void modificar(HorarioOperacion horariooperacion);

    Optional<HorarioOperacion> buscarId(Integer id);

    void eliminar(Integer id);
}
