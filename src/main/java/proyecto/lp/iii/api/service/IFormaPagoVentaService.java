package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.FormaPagoVenta;

public interface IFormaPagoVentaService {
    List<FormaPagoVenta> buscarTodos();

    void guardar(FormaPagoVenta formapagoventa);

    void modificar(FormaPagoVenta formapagoventa);

    Optional<FormaPagoVenta> buscarId(Integer id);

    void eliminar(Integer id);
}
