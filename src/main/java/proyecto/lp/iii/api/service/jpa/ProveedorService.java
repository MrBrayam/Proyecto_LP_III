package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Proveedor;
import proyecto.lp.iii.api.repository.ProveedorRepository;
import proyecto.lp.iii.api.service.IProveedorService;

@Service
public class ProveedorService implements IProveedorService {
    @Autowired
    private ProveedorRepository repoProveedor;

    public List<Proveedor> buscarTodos() {
        return repoProveedor.findAll();
    }

    public void guardar(Proveedor proveedor) {
        repoProveedor.save(proveedor);
    }

    public void modificar(Proveedor proveedor) {
        repoProveedor.save(proveedor);
    }

    public Optional<Proveedor> buscarId(Integer id) {
        return repoProveedor.findById(id);
    }

    public void eliminar(Integer id) {
        repoProveedor.deleteById(id);
    }
}
