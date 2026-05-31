package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.repository.SesionCajaRepository;
import proyecto.lp.iii.api.service.ISesionCajaService;

@Service
public class SesionCajaService implements ISesionCajaService {
    @Autowired
    private SesionCajaRepository repoSesionCaja;

    public List<SesionCaja> buscarTodos() { 
        return repoSesionCaja.findAll(); 
    }
    public void guardar(SesionCaja sesioncaja) { 
        repoSesionCaja.save(sesioncaja);
     }
    public void modificar(SesionCaja sesioncaja) {
         repoSesionCaja.save(sesioncaja);
    }
    public Optional<SesionCaja> buscarId(Integer id) {
         return repoSesionCaja.findById(id); 
    }
    public void eliminar(Integer id) {
         repoSesionCaja.deleteById(id); 
    }
}
