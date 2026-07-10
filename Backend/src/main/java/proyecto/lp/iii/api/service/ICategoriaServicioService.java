package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.CategoriaServicio;

public interface ICategoriaServicioService {
    List<CategoriaServicio> buscarTodos();

    void guardar(CategoriaServicio categoriaservicio);

    void modificar(CategoriaServicio categoriaservicio);

    Optional<CategoriaServicio> buscarId(Integer id);

    void eliminar(Integer id);
}
