package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.SesionCaja;

public interface ISesionCajaService {
    List<SesionCaja> buscarTodos();

    void guardar(SesionCaja sesioncaja);

    void modificar(SesionCaja sesioncaja);

    Optional<SesionCaja> buscarId(Integer id);

    void eliminar(Integer id);
}
