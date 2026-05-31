package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ComposicionCombo;

public interface IComposicionComboService {
    List<ComposicionCombo> buscarTodos();

    void guardar(ComposicionCombo composicionCombo);

    void modificar(ComposicionCombo composicionCombo);

    Optional<ComposicionCombo> buscarId(Integer id);

    void eliminar(Integer id);
}
