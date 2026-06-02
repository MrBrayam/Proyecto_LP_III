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

import proyecto.lp.iii.api.entity.FormaPagoVenta;
import proyecto.lp.iii.api.service.IFormaPagoVentaService;

@RestController
@RequestMapping("/api")
public class FormaPagoVentaController {
@Autowired
private IFormaPagoVentaService serviceFormaPagoVenta;

@GetMapping("/formas_pago_venta")
public List<FormaPagoVenta> buscarTodos() {
return serviceFormaPagoVenta.buscarTodos();
}

@PostMapping("/formas_pago_venta")
public FormaPagoVenta guardar(@RequestBody FormaPagoVenta registro) {
serviceFormaPagoVenta.guardar(registro);
return registro;
}

@PutMapping("/formas_pago_venta")
public FormaPagoVenta modificar(@RequestBody FormaPagoVenta registro) {
serviceFormaPagoVenta.modificar(registro);
return registro;
}

@GetMapping("/formas_pago_venta/{id}")
public Optional<FormaPagoVenta> buscarId(@PathVariable("id") Integer id) {
return serviceFormaPagoVenta.buscarId(id);
}

@DeleteMapping("/formas_pago_venta/{id}")
public String elminar(@PathVariable Integer id) {
serviceFormaPagoVenta.eliminar(id);
return "Registro Eliminado";
}
}
