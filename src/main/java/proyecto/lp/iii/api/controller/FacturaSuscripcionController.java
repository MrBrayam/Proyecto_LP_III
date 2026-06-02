package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proyecto.lp.iii.api.entity.FacturaSuscripcion;
import proyecto.lp.iii.api.service.IFacturaSuscripcionService;

@RestController
@RequestMapping("/api")
public class FacturaSuscripcionController {
    @Autowired
    private IFacturaSuscripcionService serviceFacturaSuscripcion;

    @GetMapping("/facturas_suscripcion")
    public List<FacturaSuscripcion> buscarTodos() {
        return serviceFacturaSuscripcion.buscarTodos();
    }

    @PostMapping("/facturas_suscripcion")
    public FacturaSuscripcion guardar(@RequestBody FacturaSuscripcion registro) {
    serviceFacturaSuscripcion.guardar(registro);
        return registro;
    }

    @PutMapping("/facturas_suscripcion")
    public FacturaSuscripcion modificar(@RequestBody FacturaSuscripcion registro) {
    serviceFacturaSuscripcion.modificar(registro);
        return registro;
    }

    @GetMapping("/facturas_suscripcion/{id}")
    public Optional<FacturaSuscripcion> buscarId(@PathVariable("id") Integer id) {
        return serviceFacturaSuscripcion.buscarId(id);
    }

    @DeleteMapping("/facturas_suscripcion/{id}")
    public String elminar(@PathVariable Integer id) {
    serviceFacturaSuscripcion.eliminar(id);
        return "Registro Eliminado";
    }
}
