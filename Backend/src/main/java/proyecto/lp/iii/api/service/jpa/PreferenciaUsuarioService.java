package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.PreferenciaUsuario;
import proyecto.lp.iii.api.repository.PreferenciaUsuarioRepository;
import proyecto.lp.iii.api.service.IPreferenciaUsuarioService;

@Service
public class PreferenciaUsuarioService implements IPreferenciaUsuarioService {
    @Autowired
    private PreferenciaUsuarioRepository repoPreferenciaUsuario;
    
    @Autowired
    private EntityManager entityManager;

    public List<PreferenciaUsuario> buscarTodos() { 
        return repoPreferenciaUsuario.findAll(); 
    }
    public void guardar(PreferenciaUsuario preferenciausuario) { 
        repoPreferenciaUsuario.save(preferenciausuario); 
    }
    @Transactional
    public void modificar(PreferenciaUsuario preferenciausuario) { 
        entityManager.merge(preferenciausuario); 
    }
    public Optional<PreferenciaUsuario> buscarId(Integer id) { 
        return repoPreferenciaUsuario.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPreferenciaUsuario.deleteById(id);
    }
}
