package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.repository.DetalleDevolucionProveedorRepository;
import proyecto.lp.iii.api.service.IDetalleDevolucionProveedorService;

@Service
public class DetalleDevolucionProveedorService implements IDetalleDevolucionProveedorService {
    @Autowired
    private DetalleDevolucionProveedorRepository repoDetalleDevolucionProveedor;

    public List<DetalleDevolucionProveedor> buscarTodos() {
        return repoDetalleDevolucionProveedor.findAll();
    }

    public void guardar(DetalleDevolucionProveedor detalleDevolucionProveedor) {
        repoDetalleDevolucionProveedor.save(detalleDevolucionProveedor);
    }

    public void modificar(DetalleDevolucionProveedor detalleDevolucionProveedor) {
        repoDetalleDevolucionProveedor.save(detalleDevolucionProveedor);
    }

    public Optional<DetalleDevolucionProveedor> buscarId(Integer id) {
        return repoDetalleDevolucionProveedor.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetalleDevolucionProveedor.deleteById(id);
    }
}
