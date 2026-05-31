package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.UsuarioSede;
import proyecto.lp.iii.api.repository.UsuarioSedeRepository;
import proyecto.lp.iii.api.service.IUsuarioSedeService;

@Service
public class UsuarioSedeService implements IUsuarioSedeService {
    @Autowired
    private UsuarioSedeRepository repoUsuarioSede;

    public List<UsuarioSede> buscarTodos() { 
        return repoUsuarioSede.findAll(); 
    }
    public void guardar(UsuarioSede usuariosede) { 
        repoUsuarioSede.save(usuariosede); 
    }
    public void modificar(UsuarioSede usuariosede) { 
        repoUsuarioSede.save(usuariosede); 
    }
    public Optional<UsuarioSede> buscarId(Integer id) { 
        return repoUsuarioSede.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoUsuarioSede.deleteById(id); 
    }
}
