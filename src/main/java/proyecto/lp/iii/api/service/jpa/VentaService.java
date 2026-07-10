package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.repository.VentaRepository;
import proyecto.lp.iii.api.service.IVentaService;

@Service
public class VentaService implements IVentaService {
    @Autowired
    private VentaRepository repoVenta;
    
    @Autowired
    private EntityManager entityManager;

    public List<Venta> buscarTodos() { 
        return repoVenta.findAll(); 
    }
    
    public Venta guardar(Venta venta) { 
        long count = repoVenta.count();
        
        if (venta.getNumero_ticket() == null || venta.getNumero_ticket().trim().isEmpty()) {
            venta.setNumero_ticket("TKT-" + String.format("%08d", count + 1));
        }
        
        if (venta.getComprobante_numero() == null || venta.getComprobante_numero().trim().isEmpty() || venta.getComprobante_numero().startsWith("C-")) {
            String tipo = venta.getTipo_comprobante();
            String prefix = "T001-";
            if ("factura".equalsIgnoreCase(tipo)) {
                prefix = "F001-";
            } else if ("boleta".equalsIgnoreCase(tipo)) {
                prefix = "B001-";
            }
            venta.setComprobante_numero(prefix + String.format("%08d", count + 1));
        }

        if (venta.getFecha_venta() == null) {
            venta.setFecha_venta(LocalDateTime.now());
        }

        return repoVenta.save(venta);
    }
    
    @Transactional
    public void modificar(Venta venta) { 
        Optional<Venta> originalOpt = repoVenta.findById(venta.getId_ventas());
        if (originalOpt.isPresent()) {
            Venta original = originalOpt.get();
            if (venta.getNumero_ticket() == null || venta.getNumero_ticket().trim().isEmpty()) {
                venta.setNumero_ticket(original.getNumero_ticket());
            }
            if (venta.getComprobante_numero() == null || venta.getComprobante_numero().trim().isEmpty()) {
                venta.setComprobante_numero(original.getComprobante_numero());
            }
            if (venta.getFecha_venta() == null) {
                venta.setFecha_venta(original.getFecha_venta());
            }
        }
        entityManager.merge(venta); 
    }
    
    public Optional<Venta> buscarId(Integer id) {
         return repoVenta.findById(id); 
    }
    public void eliminar(Integer id) {
         repoVenta.deleteById(id); 
    }
}
