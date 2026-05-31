package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.repository.TenantsRepository;
import proyecto.lp.iii.api.service.ITenantsService;


@Service
public class TenantsService implements ITenantsService {
    @Autowired
     private TenantsRepository repoTenants;

    public List<Tenants> buscarTodos(){
        return repoTenants.findAll();
    }

    public void guardar(Tenants curso){
        repoTenants.save(curso);
    }

    public void modificar(Tenants curso){
        repoTenants.save(curso);
    }

    public Optional<Tenants> buscarId(Integer id){
        return repoTenants.findById(id);
    }
     
    public void eliminar(Integer id){
        repoTenants.deleteById(id);
    }
}
