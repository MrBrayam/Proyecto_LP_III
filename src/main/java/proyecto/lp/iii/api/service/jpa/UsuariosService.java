package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.service.IUsuariosService;
import proyecto.lp.iii.api.repository.UsuariosRepository;


@Service
public class UsuariosService implements IUsuariosService {
  @Autowired
    private UsuariosRepository repoUsuarios;
    
  @Autowired
    private EntityManager entityManager;

    public List<Usuarios> buscarTodos(){
        return repoUsuarios.findAll();
    }

    public void guardar(Usuarios usuario){
        repoUsuarios.save(usuario);
    }

    @Transactional
    public void modificar(Usuarios usuario){
        entityManager.merge(usuario);
    }

    public Optional<Usuarios> buscarId(Integer id){
        return repoUsuarios.findById(id);
    }
     
    public void eliminar(Integer id){
        repoUsuarios.deleteById(id);
    }
}
