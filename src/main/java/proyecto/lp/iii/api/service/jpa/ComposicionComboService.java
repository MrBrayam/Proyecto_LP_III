package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.repository.ComposicionComboRepository;
import proyecto.lp.iii.api.service.IComposicionComboService;

@Service
public class ComposicionComboService implements IComposicionComboService {
    @Autowired
    private ComposicionComboRepository repoComposicionCombo;

    public List<ComposicionCombo> buscarTodos() {
        return repoComposicionCombo.findAll();
    }

    public void guardar(ComposicionCombo composicioncombo) {
        repoComposicionCombo.save(composicioncombo);
    }

    public void modificar(ComposicionCombo composicioncombo) {
        repoComposicionCombo.save(composicioncombo);
    }

    public Optional<ComposicionCombo> buscarId(Integer id) {
        return repoComposicionCombo.findById(id);
    }

    public void eliminar(Integer id) {
        repoComposicionCombo.deleteById(id);
    }
}
