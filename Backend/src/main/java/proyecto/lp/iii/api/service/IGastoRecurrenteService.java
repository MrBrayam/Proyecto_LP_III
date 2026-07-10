package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.GastoRecurrente;

public interface IGastoRecurrenteService {
    List<GastoRecurrente> buscarTodos();

    void guardar(GastoRecurrente gastorecurrente);

    void modificar(GastoRecurrente gastorecurrente);

    Optional<GastoRecurrente> buscarId(Integer id);

    void eliminar(Integer id);
}
