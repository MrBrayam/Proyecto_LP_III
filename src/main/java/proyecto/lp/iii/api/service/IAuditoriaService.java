package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Auditoria;

public interface IAuditoriaService {
    List<Auditoria> buscarTodos();

    void guardar(Auditoria auditoria);

    void modificar(Auditoria auditoria);

    Optional<Auditoria> buscarId(Integer id);

    void eliminar(Integer id);
}
