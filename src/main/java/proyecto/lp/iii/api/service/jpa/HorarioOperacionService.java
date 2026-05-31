package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.HorarioOperacion;
import proyecto.lp.iii.api.repository.HorarioOperacionRepository;
import proyecto.lp.iii.api.service.IHorarioOperacionService;

@Service
public class HorarioOperacionService implements IHorarioOperacionService {
    @Autowired
    private HorarioOperacionRepository repoHorarioOperacion;

    public List<HorarioOperacion> buscarTodos() { 
        return repoHorarioOperacion.findAll(); 
    }
    public void guardar(HorarioOperacion horariooperacion) { 
        repoHorarioOperacion.save(horariooperacion); 
    }
    public void modificar(HorarioOperacion horariooperacion) { 
        repoHorarioOperacion.save(horariooperacion); 
    }
    public Optional<HorarioOperacion> buscarId(Integer id) { 
        return repoHorarioOperacion.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoHorarioOperacion.deleteById(id); 
    }
}
