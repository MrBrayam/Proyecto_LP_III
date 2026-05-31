package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.repository.ProductoRepository;
import proyecto.lp.iii.api.service.IProductoService;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository repoProducto;

    public List<Producto> buscarTodos() { 
        return repoProducto.findAll(); 
    }
    public void guardar(Producto producto) { 
        repoProducto.save(producto); 
    }
    public void modificar(Producto producto) {
        repoProducto.save(producto); 
    }
    public Optional<Producto> buscarId(Integer id) { 
        return repoProducto.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoProducto.deleteById(id); 
    }
}
