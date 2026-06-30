package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.repository.DetalleDevolucionProveedorRepository;
import proyecto.lp.iii.api.service.IDetalleDevolucionProveedorService;

@Service
public class DetalleDevolucionProveedorService implements IDetalleDevolucionProveedorService {
    @Autowired
    private DetalleDevolucionProveedorRepository repoDetalleDevolucionProveedor;
    
    @Autowired
    private EntityManager entityManager;

    public List<DetalleDevolucionProveedor> buscarTodos() {
        return repoDetalleDevolucionProveedor.findAll();
    }

    public void guardar(DetalleDevolucionProveedor detalledevolucionproveedor) {
        repoDetalleDevolucionProveedor.save(detalledevolucionproveedor);
    }

    @Transactional
    public void modificar(DetalleDevolucionProveedor detalledevolucionproveedor) {
        entityManager.merge(detalledevolucionproveedor);
    }

    public Optional<DetalleDevolucionProveedor> buscarId(Integer id) {
        return repoDetalleDevolucionProveedor.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetalleDevolucionProveedor.deleteById(id);
    }
}
