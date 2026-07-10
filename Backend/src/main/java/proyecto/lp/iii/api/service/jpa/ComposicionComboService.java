package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.ComposicionCombo;
import proyecto.lp.iii.api.repository.ComposicionComboRepository;
import proyecto.lp.iii.api.service.IComposicionComboService;

@Service
public class ComposicionComboService implements IComposicionComboService {
    @Autowired
    private ComposicionComboRepository repoComposicionCombo;
    
    @Autowired
    private EntityManager entityManager;

    public List<ComposicionCombo> buscarTodos() {
        return repoComposicionCombo.findAll();
    }

    public List<ComposicionCombo> buscarPorCombo(Integer comboId) {
        return repoComposicionCombo.findByComboId(comboId);
    }

    public void guardar(ComposicionCombo composicioncombo) {
        repoComposicionCombo.save(composicioncombo);
    }

    @Transactional
    public void modificar(ComposicionCombo composicioncombo) {
        entityManager.merge(composicioncombo);
    }

    public Optional<ComposicionCombo> buscarId(Integer id) {
        return repoComposicionCombo.findById(id);
    }

    public void eliminar(Integer id) {
        repoComposicionCombo.deleteById(id);
    }

    @Transactional
    public void eliminarPorCombo(Integer comboId) {
        repoComposicionCombo.deleteByComboId(comboId);
    }
}

