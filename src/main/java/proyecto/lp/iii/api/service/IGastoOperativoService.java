package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.GastoOperativo;

public interface IGastoOperativoService {
    List<GastoOperativo> buscarTodos();

    void guardar(GastoOperativo gastooperativo);

    void modificar(GastoOperativo gastooperativo);

    Optional<GastoOperativo> buscarId(Integer id);

    void eliminar(Integer id);
}
