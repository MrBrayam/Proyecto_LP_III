package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Venta;

public interface IVentaService {
    List<Venta> buscarTodos();

    void guardar(Venta venta);

    void modificar(Venta venta);

    Optional<Venta> buscarId(Integer id);

    void eliminar(Integer id);
}
