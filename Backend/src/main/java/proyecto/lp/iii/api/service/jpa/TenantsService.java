package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.repository.TenantsRepository;
import proyecto.lp.iii.api.service.ITenantsService;


@Service
public class TenantsService implements ITenantsService {
    @Autowired
     private TenantsRepository repoTenants;
    
    @Autowired
    private EntityManager entityManager;

    public List<Tenants> buscarTodos(){
        return repoTenants.findAll();
    }

    @Transactional
    public void guardar(Tenants tenant){
        repoTenants.saveAndFlush(tenant);
    }

    @Transactional
    public void modificar(Tenants tenant){
        entityManager.merge(tenant);
    }

    public Optional<Tenants> buscarId(Integer id){
        return repoTenants.findById(id);
    }
     
    public void eliminar(Integer id){
        repoTenants.deleteById(id);
    }
}
