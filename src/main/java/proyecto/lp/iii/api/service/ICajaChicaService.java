package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.CajaChica;

public interface ICajaChicaService {
    List<CajaChica> buscarTodos();

    void guardar(CajaChica cajaChica);

    void modificar(CajaChica cajaChica);

    Optional<CajaChica> buscarId(Integer id);

    void eliminar(Integer id);
}
