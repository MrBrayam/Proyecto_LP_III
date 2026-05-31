package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.repository.AlmacenRepository;
import proyecto.lp.iii.api.service.IAlmacenService;

@Service
public class AlmacenService implements IAlmacenService {
    @Autowired
    private AlmacenRepository repoAlmacen;

    public List<Almacen> buscarTodos() {
        return repoAlmacen.findAll();
    }

    public void guardar(Almacen almacen) {
        repoAlmacen.save(almacen);
    }

    public void modificar(Almacen almacen) {
        repoAlmacen.save(almacen);
    }

    public Optional<Almacen> buscarId(Integer id) {
        return repoAlmacen.findById(id);
    }

    public void eliminar(Integer id) {
        repoAlmacen.deleteById(id);
    }
}
