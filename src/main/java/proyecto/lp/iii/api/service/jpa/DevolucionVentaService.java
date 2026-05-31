package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.DevolucionVenta;
import proyecto.lp.iii.api.repository.DevolucionVentaRepository;
import proyecto.lp.iii.api.service.IDevolucionVentaService;

@Service
public class DevolucionVentaService implements IDevolucionVentaService {
    @Autowired
    private DevolucionVentaRepository repoDevolucionVenta;

    public List<DevolucionVenta> buscarTodos() {
        return repoDevolucionVenta.findAll();
    }

    public void guardar(DevolucionVenta devolucionVenta) {
        repoDevolucionVenta.save(devolucionVenta);
    }

    public void modificar(DevolucionVenta devolucionVenta) {
        repoDevolucionVenta.save(devolucionVenta);
    }

    public Optional<DevolucionVenta> buscarId(Integer id) {
        return repoDevolucionVenta.findById(id);
    }

    public void eliminar(Integer id) {
        repoDevolucionVenta.deleteById(id);
    }
}
