package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.MetodoPago;

public interface IMetodoPagoService {
    List<MetodoPago> buscarTodos();
    void guardar(MetodoPago entity);
    void modificar(MetodoPago entity);
    Optional<MetodoPago> buscarId(Integer id);
    void eliminar(Integer id);
}
