package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.repository.ComboPromocionalRepository;
import proyecto.lp.iii.api.service.IComboPromocionalService;

@Service
public class ComboPromocionalService implements IComboPromocionalService {
    @Autowired
    private ComboPromocionalRepository repoComboPromocional;

    public List<ComboPromocional> buscarTodos() {
        return repoComboPromocional.findAll();
    }

    public void guardar(ComboPromocional comboPromocional) {
        repoComboPromocional.save(comboPromocional);
    }

    public void modificar(ComboPromocional comboPromocional) {
        repoComboPromocional.save(comboPromocional);
    }

    public Optional<ComboPromocional> buscarId(Integer id) {
        return repoComboPromocional.findById(id);
    }

    public void eliminar(Integer id) {
        repoComboPromocional.deleteById(id);
    }
}
