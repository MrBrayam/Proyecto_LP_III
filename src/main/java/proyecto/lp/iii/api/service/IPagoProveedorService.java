package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.PagoProveedor;

public interface IPagoProveedorService {
    List<PagoProveedor> buscarTodos();

    void guardar(PagoProveedor pagoproveedor);

    void modificar(PagoProveedor pagoproveedor);

    Optional<PagoProveedor> buscarId(Integer id);

    void eliminar(Integer id);
}
