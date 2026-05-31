package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ProveedorCategoria;
import proyecto.lp.iii.api.repository.ProveedorCategoriaRepository;
import proyecto.lp.iii.api.service.IProveedorCategoriaService;

@Service
public class ProveedorCategoriaService implements IProveedorCategoriaService {
    @Autowired
    private ProveedorCategoriaRepository repo;

    public List<ProveedorCategoria> buscarTodos() {
        return repo.findAll();
    }

    public void guardar(ProveedorCategoria p) {
        repo.save(p);
    }

    public void modificar(ProveedorCategoria p) {
        repo.save(p);
    }

    public Optional<ProveedorCategoria> buscarId(Integer id) {
        return repo.findById(id);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
