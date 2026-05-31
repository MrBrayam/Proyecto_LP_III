package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import proyecto.lp.iii.api.repository.FormaPagoVentaRepository;
import proyecto.lp.iii.api.service.IFormaPagoVentaService;

@Service
public class FormaPagoVentaService implements IFormaPagoVentaService {
    @Autowired
    private FormaPagoVentaRepository repoFormaPagoVenta;

    public List<FormaPagoVenta> buscarTodos() { 
        return repoFormaPagoVenta.findAll();
    }
    public void guardar(FormaPagoVenta formapagoventa) { 
        repoFormaPagoVenta.save(formapagoventa); 
    }
    public void modificar(FormaPagoVenta formapagoventa) { 
        repoFormaPagoVenta.save(formapagoventa); 
    }
    public Optional<FormaPagoVenta> buscarId(Integer id) { 
        return repoFormaPagoVenta.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoFormaPagoVenta.deleteById(id); 
    }
}
