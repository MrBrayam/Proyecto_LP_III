package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.FacturaSuscripcion;

public interface IFacturaSuscripcionService {
    List<FacturaSuscripcion> buscarTodos();

    void guardar(FacturaSuscripcion facturasuscripcion);

    void modificar(FacturaSuscripcion facturasuscripcion);

    Optional<FacturaSuscripcion> buscarId(Integer id);

    void eliminar(Integer id);
}
