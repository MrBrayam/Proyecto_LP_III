package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.repository.PermisoRolRepository;
import proyecto.lp.iii.api.service.IPermisoRolService;

@Service
public class PermisoRolService implements IPermisoRolService {
    @Autowired
    private PermisoRolRepository repoPermisoRol;

    public List<PermisoRol> buscarTodos() { 
        return repoPermisoRol.findAll(); 
    }
    public void guardar(PermisoRol permisorol) { 
        repoPermisoRol.save(permisorol); 
    }
    public void modificar(PermisoRol permisorol) { 
        repoPermisoRol.save(permisorol); 
    }
    public Optional<PermisoRol> buscarId(Integer id) { 
        return repoPermisoRol.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPermisoRol.deleteById(id); 
    }
}
