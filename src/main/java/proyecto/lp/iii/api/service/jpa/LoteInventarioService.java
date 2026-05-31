package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.repository.LoteInventarioRepository;
import proyecto.lp.iii.api.service.ILoteInventarioService;

@Service
public class LoteInventarioService implements ILoteInventarioService {
    @Autowired
    private LoteInventarioRepository repoLoteInventario;

    public List<LoteInventario> buscarTodos() { 
        return repoLoteInventario.findAll(); 
    }
    public void guardar(LoteInventario loteinventario) { 
        repoLoteInventario.save(loteinventario); 
    }
    public void modificar(LoteInventario loteinventario) { 
        repoLoteInventario.save(loteinventario); 
    }
    public Optional<LoteInventario> buscarId(Integer id) { 
        return repoLoteInventario.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoLoteInventario.deleteById(id); 
    }
}
