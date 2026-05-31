package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.SerieComprobante;

public interface ISerieComprobanteService {
    List<SerieComprobante> buscarTodos();
    void guardar(SerieComprobante entity);
    void modificar(SerieComprobante entity);
    Optional<SerieComprobante> buscarId(Integer id);
    void eliminar(Integer id);
}
