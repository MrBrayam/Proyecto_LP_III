package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.service.IUsuariosService;
import proyecto.lp.iii.api.repository.UsuariosRepository;


@Service
public class UsuariosService implements IUsuariosService {
  @Autowired
    private UsuariosRepository repoUsuarios;

    public List<Usuarios> buscarTodos(){
        return repoUsuarios.findAll();
    }

    public void guardar(Usuarios usuario){
        repoUsuarios.save(usuario);
    }

    public void modificar(Usuarios usuario){
        repoUsuarios.save(usuario);
    }

    public Optional<Usuarios> buscarId(Integer id){
        return repoUsuarios.findById(id);
    }
     
    public void eliminar(Integer id){
        repoUsuarios.deleteById(id);
    }
}
