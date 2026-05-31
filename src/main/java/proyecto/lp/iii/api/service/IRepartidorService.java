package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;
import proyecto.lp.iii.api.entity.Repartidor;

public interface IRepartidorService {
    List<Repartidor> buscarTodos();

    void guardar(Repartidor repartidor);

    void modificar(Repartidor repartidor);

    Optional<Repartidor> buscarId(Integer id);

    void eliminar(Integer id);
}
