package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ImagenProducto;
import proyecto.lp.iii.api.repository.ImagenProductoRepository;
import proyecto.lp.iii.api.service.IImagenProductoService;

@Service
public class ImagenProductoService implements IImagenProductoService {
    @Autowired
    private ImagenProductoRepository repoImagenProducto;

    public List<ImagenProducto> buscarTodos() { 
        return repoImagenProducto.findAll(); 
    }
    public void guardar(ImagenProducto imagenproducto) { 
        repoImagenProducto.save(imagenproducto); 
    }
    public void modificar(ImagenProducto imagenproducto) { 
        repoImagenProducto.save(imagenproducto); 
    }
    public Optional<ImagenProducto> buscarId(Integer id) { 
        return repoImagenProducto.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoImagenProducto.deleteById(id); 
    }
}
