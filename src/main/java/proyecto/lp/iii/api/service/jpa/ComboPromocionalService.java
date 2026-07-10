package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.repository.ComboPromocionalRepository;
import proyecto.lp.iii.api.service.IComboPromocionalService;

@Service
public class ComboPromocionalService implements IComboPromocionalService {
    @Autowired
    private ComboPromocionalRepository repoComboPromocional;
    
    @Autowired
    private EntityManager entityManager;

    public List<ComboPromocional> buscarTodos() {
        return repoComboPromocional.findAll();
    }

    public ComboPromocional guardar(ComboPromocional combopromocional) {
        return repoComboPromocional.save(combopromocional);
    }

    @Transactional
    public void modificar(ComboPromocional combopromocional) {
        entityManager.merge(combopromocional);
    }

    public Optional<ComboPromocional> buscarId(Integer id) {
        return repoComboPromocional.findById(id);
    }

    public void eliminar(Integer id) {
        repoComboPromocional.deleteById(id);
    }
}
