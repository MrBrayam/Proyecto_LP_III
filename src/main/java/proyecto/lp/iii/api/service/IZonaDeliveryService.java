package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.ZonaDelivery;

public interface IZonaDeliveryService {
    List<ZonaDelivery> buscarTodos();

    void guardar(ZonaDelivery zonadelivery);

    void modificar(ZonaDelivery zonadelivery);

    Optional<ZonaDelivery> buscarId(Integer id);

    void eliminar(Integer id);
}
