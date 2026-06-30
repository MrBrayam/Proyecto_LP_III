package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.repository.PermisoRolRepository;
import proyecto.lp.iii.api.service.IPermisoRolService;

@Service
public class PermisoRolService implements IPermisoRolService {
    @Autowired
    private PermisoRolRepository repoPermisoRol;
    
    @Autowired
    private EntityManager entityManager;

    public List<PermisoRol> buscarTodos() { 
        return repoPermisoRol.findAll(); 
    }
    public void guardar(PermisoRol permisorol) { 
        repoPermisoRol.save(permisorol); 
    }
    @Transactional
    public void modificar(PermisoRol permisorol) { 
        entityManager.merge(permisorol); 
    }
    public Optional<PermisoRol> buscarId(Integer id) { 
        return repoPermisoRol.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoPermisoRol.deleteById(id); 
    }
}
