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

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.service.IVentaService;

@RestController
@RequestMapping("/api")
public class VentaController {
@Autowired
private IVentaService serviceVenta;

@GetMapping("/ventas")
public List<Venta> buscarTodos() {
return serviceVenta.buscarTodos();
}

@PostMapping("/ventas")
public Venta guardar(@RequestBody Venta registro) {
serviceVenta.guardar(registro);
return registro;
}

@PutMapping("/ventas")
public Venta modificar(@RequestBody Venta registro) {
serviceVenta.modificar(registro);
return registro;
}

@GetMapping("/ventas/{id}")
public Optional<Venta> buscarId(@PathVariable("id") Integer id) {
return serviceVenta.buscarId(id);
}

@DeleteMapping("/ventas/{id}")
public String elminar(@PathVariable Integer id) {
serviceVenta.eliminar(id);
return "Registro Eliminado";
}
}
