package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.repository.DetalleVentaRepository;
import proyecto.lp.iii.api.service.IDetalleVentaService;

@Service
public class DetalleVentaService implements IDetalleVentaService {
    @Autowired
    private DetalleVentaRepository repoDetalleVenta;

    public List<DetalleVenta> buscarTodos() {
        return repoDetalleVenta.findAll();
    }

    public void guardar(DetalleVenta detalleVenta) {
        repoDetalleVenta.save(detalleVenta);
    }

    public void modificar(DetalleVenta detalleVenta) {
        repoDetalleVenta.save(detalleVenta);
    }

    public Optional<DetalleVenta> buscarId(Integer id) {
        return repoDetalleVenta.findById(id);
    }

    public void eliminar(Integer id) {
        repoDetalleVenta.deleteById(id);
    }
}
