package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void modificar(Almacen almacen) {
        Almacen existente = repoAlmacen.findById(almacen.getId_almacenes())
                .orElseThrow(() -> new RuntimeException("Almacen no encontrado con id: " + almacen.getId_almacenes()));
        if (almacen.getId_sedes() != null) {
            existente.setId_sedes(almacen.getId_sedes());
        }
        existente.setNombre_almacen(almacen.getNombre_almacen());
        existente.setUbicacion(almacen.getUbicacion());
        existente.setCapacidad(almacen.getCapacidad());
        repoAlmacen.save(existente);
    }

    public Optional<Almacen> buscarId(Integer id) {
        return repoAlmacen.findById(id);
    }

    public void eliminar(Integer id) {
        repoAlmacen.deleteById(id);
    }
}
