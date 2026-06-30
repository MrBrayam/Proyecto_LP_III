package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.RolPersonalizado;
import proyecto.lp.iii.api.repository.RolPersonalizadoRepository;
import proyecto.lp.iii.api.service.IRolPersonalizadoService;

@Service
public class RolPersonalizadoService implements IRolPersonalizadoService {
    @Autowired
    private RolPersonalizadoRepository repoRolPersonalizado;
    
    @Autowired
    private EntityManager entityManager;

    public List<RolPersonalizado> buscarTodos() {
        return repoRolPersonalizado.findAll();
    }

    public void guardar(RolPersonalizado rolPersonalizado) {
        repoRolPersonalizado.save(rolPersonalizado);
    }

    @Transactional
    public void modificar(RolPersonalizado rolPersonalizado) {
        entityManager.merge(rolPersonalizado);
    }

    public Optional<RolPersonalizado> buscarId(Integer id) {
        return repoRolPersonalizado.findById(id);
    }

    public void eliminar(Integer id) {
        repoRolPersonalizado.deleteById(id);
    }
}
