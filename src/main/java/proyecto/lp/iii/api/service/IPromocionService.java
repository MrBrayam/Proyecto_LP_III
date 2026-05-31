package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Promocion;

public interface IPromocionService {
    List<Promocion> buscarTodos();

    void guardar(Promocion promocion);

    void modificar(Promocion promocion);

    Optional<Promocion> buscarId(Integer id);

    void eliminar(Integer id);
}
