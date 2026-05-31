package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.repository.VentaRepository;
import proyecto.lp.iii.api.service.IVentaService;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private VentaRepository repoVenta;

    public List<Venta> buscarTodos() { 
        return repoVenta.findAll(); 
    }
    public void guardar(Venta venta) { 
        repoVenta.save(venta);
    }
    public void modificar(Venta venta) { 
        repoVenta.save(venta); 
    }
    public Optional<Venta> buscarId(Integer id) {
         return repoVenta.findById(id); 
    }
    public void eliminar(Integer id) {
         repoVenta.deleteById(id); 
    }
}
