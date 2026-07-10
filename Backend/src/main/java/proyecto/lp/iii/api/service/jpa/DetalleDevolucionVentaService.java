package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import proyecto.lp.iii.api.repository.DetalleDevolucionVentaRepository;
import proyecto.lp.iii.api.service.IDetalleDevolucionVentaService;

@Service
public class DetalleDevolucionVentaService implements IDetalleDevolucionVentaService {
    @Autowired
    private DetalleDevolucionVentaRepository repoDetalleDevolucionVenta;
    
    @Autowired
    private EntityManager entityManager;

    public List<DetalleDevolucionVenta> buscarTodos() {
        return repoDetalleDevolucionVenta.findAll();
    }

    public void guardar(DetalleDevolucionVenta detalledevolucionventa) {
        repoDetalleDevolucionVenta.save(detalledevolucionventa);
    }

    @Transactional
    public void modificar(DetalleDevolucionVenta detalledevolucionventa) {
        entityManager.merge(detalledevolucionventa);
    }

    public Optional<DetalleDevolucionVenta> buscarId(Integer id) {
        return repoDetalleDevolucionVenta.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetalleDevolucionVenta.deleteById(id);
    }
}
