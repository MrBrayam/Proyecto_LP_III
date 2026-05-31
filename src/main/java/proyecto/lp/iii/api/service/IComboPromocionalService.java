package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ComboPromocional;

public interface IComboPromocionalService {
    List<ComboPromocional> buscarTodos();

    void guardar(ComboPromocional comboPromocional);

    void modificar(ComboPromocional comboPromocional);

    Optional<ComboPromocional> buscarId(Integer id);

    void eliminar(Integer id);
}
