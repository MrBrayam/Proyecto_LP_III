package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Almacen;

public interface IAlmacenService {
    List<Almacen> buscarTodos();

    void guardar(Almacen almacen);

    void modificar(Almacen almacen);

    Optional<Almacen> buscarId(Integer id);

    void eliminar(Integer id);
}
