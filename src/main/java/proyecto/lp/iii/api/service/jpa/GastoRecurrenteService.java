package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.GastoRecurrente;
import proyecto.lp.iii.api.repository.GastoRecurrenteRepository;
import proyecto.lp.iii.api.service.IGastoRecurrenteService;

@Service
public class GastoRecurrenteService implements IGastoRecurrenteService {
    @Autowired
    private GastoRecurrenteRepository repoGastoRecurrente;

    public List<GastoRecurrente> buscarTodos() { 
        return repoGastoRecurrente.findAll(); 
    }
    public void guardar(GastoRecurrente gastorecurrente) { 
        repoGastoRecurrente.save(gastorecurrente); 
    }
    public void modificar(GastoRecurrente gastorecurrente) { 
        repoGastoRecurrente.save(gastorecurrente); 
    }
    public Optional<GastoRecurrente> buscarId(Integer id) { 
        return repoGastoRecurrente.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoGastoRecurrente.deleteById(id); 
    }
}
