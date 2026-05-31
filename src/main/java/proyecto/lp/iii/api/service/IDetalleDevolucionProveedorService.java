package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;

public interface IDetalleDevolucionProveedorService {
    List<DetalleDevolucionProveedor> buscarTodos();

    void guardar(DetalleDevolucionProveedor detalleDevolucionProveedor);

    void modificar(DetalleDevolucionProveedor detalleDevolucionProveedor);

    Optional<DetalleDevolucionProveedor> buscarId(Integer id);

    void eliminar(Integer id);
}
