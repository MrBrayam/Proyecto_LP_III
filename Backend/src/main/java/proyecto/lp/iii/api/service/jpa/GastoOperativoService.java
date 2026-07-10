package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.GastoOperativo;
import proyecto.lp.iii.api.repository.GastoOperativoRepository;
import proyecto.lp.iii.api.service.IGastoOperativoService;

@Service
public class GastoOperativoService implements IGastoOperativoService {
    @Autowired
    private GastoOperativoRepository repoGastoOperativo;
    
    @Autowired
    private EntityManager entityManager;

    public List<GastoOperativo> buscarTodos() { 
        return repoGastoOperativo.findAll(); 
    }
    public void guardar(GastoOperativo gastooperativo) { 
        repoGastoOperativo.save(gastooperativo); 
    }
    @Transactional
    public void modificar(GastoOperativo gastooperativo) { 
        entityManager.merge(gastooperativo); 
    }
    public Optional<GastoOperativo> buscarId(Integer id) { 
        return repoGastoOperativo.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoGastoOperativo.deleteById(id); 
    }
}
