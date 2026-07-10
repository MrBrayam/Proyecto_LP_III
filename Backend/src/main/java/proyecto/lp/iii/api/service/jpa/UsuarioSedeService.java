package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.UsuarioSede;
import proyecto.lp.iii.api.repository.UsuarioSedeRepository;
import proyecto.lp.iii.api.service.IUsuarioSedeService;

@Service
public class UsuarioSedeService implements IUsuarioSedeService {
    @Autowired
    private UsuarioSedeRepository repoUsuarioSede;
    
    @Autowired
    private EntityManager entityManager;

    public List<UsuarioSede> buscarTodos() { 
        return repoUsuarioSede.findAll(); 
    }
    public void guardar(UsuarioSede usuariosede) { 
        repoUsuarioSede.save(usuariosede); 
    }
    @Transactional
    public void modificar(UsuarioSede usuariosede) { 
        entityManager.merge(usuariosede); 
    }
    public Optional<UsuarioSede> buscarId(Integer id) { 
        return repoUsuarioSede.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoUsuarioSede.deleteById(id); 
    }
}
