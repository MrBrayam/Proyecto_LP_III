package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.DevolucionProveedor;
import proyecto.lp.iii.api.repository.DevolucionProveedorRepository;
import proyecto.lp.iii.api.service.IDevolucionProveedorService;

@Service
public class DevolucionProveedorService implements IDevolucionProveedorService {
    @Autowired
    private DevolucionProveedorRepository repoDevolucionProveedor;

    public List<DevolucionProveedor> buscarTodos() {
        return repoDevolucionProveedor.findAll();
    }

    public void guardar(DevolucionProveedor devolucionproveedor) {
        repoDevolucionProveedor.save(devolucionproveedor);
    }

    public void modificar(DevolucionProveedor devolucionproveedor) {
        repoDevolucionProveedor.save(devolucionproveedor);
    }

    public Optional<DevolucionProveedor> buscarId(Integer id) {
        return repoDevolucionProveedor.findById(id);
    }

    public void eliminar(Integer id) {
        repoDevolucionProveedor.deleteById(id);
    }
}
