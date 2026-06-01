package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.CategoriaServicio;
import proyecto.lp.iii.api.repository.CategoriaServicioRepository;
import proyecto.lp.iii.api.service.ICategoriaServicioService;

@Service
public class CategoriaServicioService implements ICategoriaServicioService {
    @Autowired
    private CategoriaServicioRepository repoCategoriaServicio;

    public List<CategoriaServicio> buscarTodos() {
        return repoCategoriaServicio.findAll();
    }

    public void guardar(CategoriaServicio categoriaservicio) {
        repoCategoriaServicio.save(categoriaservicio);
    }

    public void modificar(CategoriaServicio categoriaservicio) {
        repoCategoriaServicio.save(categoriaservicio);
    }

    public Optional<CategoriaServicio> buscarId(Integer id) {
        return repoCategoriaServicio.findById(id);
    }

    public void eliminar(Integer id) {
        repoCategoriaServicio.deleteById(id);
    }
}
