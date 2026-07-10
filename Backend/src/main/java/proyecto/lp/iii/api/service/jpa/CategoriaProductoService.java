package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.repository.CategoriaProductoRepository;
import proyecto.lp.iii.api.service.ICategoriaProductoService;

@Service
public class CategoriaProductoService implements ICategoriaProductoService {
    @Autowired
    private CategoriaProductoRepository repoCategoriaProducto;
    
    @Autowired
    private EntityManager entityManager;

    public List<CategoriaProducto> buscarTodos() {
        return repoCategoriaProducto.findAll();
    }

    public void guardar(CategoriaProducto categoriaproducto) {
        repoCategoriaProducto.save(categoriaproducto);
    }

    @Transactional
    public void modificar(CategoriaProducto categoriaproducto) {
        entityManager.merge(categoriaproducto);
    }

    public Optional<CategoriaProducto> buscarId(Integer id) {
        return repoCategoriaProducto.findById(id);
    }

    public void eliminar(Integer id) {
        repoCategoriaProducto.deleteById(id);
    }
}
