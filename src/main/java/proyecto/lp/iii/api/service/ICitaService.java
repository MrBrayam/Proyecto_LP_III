package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Cita;

public interface ICitaService {
    List<Cita> buscarTodos();

    void guardar(Cita cita);

    void modificar(Cita cita);

    Optional<Cita> buscarId(Integer id);

    void eliminar(Integer id);
}
