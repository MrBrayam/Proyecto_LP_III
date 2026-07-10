package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ComposicionCombo;

public interface IComposicionComboService {
    List<ComposicionCombo> buscarTodos();

    List<ComposicionCombo> buscarPorCombo(Integer comboId);

    void guardar(ComposicionCombo composicioncombo);

    void modificar(ComposicionCombo composicioncombo);

    Optional<ComposicionCombo> buscarId(Integer id);

    void eliminar(Integer id);

    void eliminarPorCombo(Integer comboId);
}
