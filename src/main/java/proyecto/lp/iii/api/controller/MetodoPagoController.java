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

import proyecto.lp.iii.api.entity.MetodoPago;
import proyecto.lp.iii.api.service.IMetodoPagoService;

@RestController
@RequestMapping("/api")
public class MetodoPagoController {
@Autowired
private IMetodoPagoService serviceMetodoPago;

@GetMapping("/metodos_pago")
public List<MetodoPago> buscarTodos() {
return serviceMetodoPago.buscarTodos();
}

@PostMapping("/metodos_pago")
public MetodoPago guardar(@RequestBody MetodoPago registro) {
serviceMetodoPago.guardar(registro);
return registro;
}

@PutMapping("/metodos_pago")
public MetodoPago modificar(@RequestBody MetodoPago registro) {
serviceMetodoPago.modificar(registro);
return registro;
}

@GetMapping("/metodos_pago/{id}")
public Optional<MetodoPago> buscarId(@PathVariable("id") Integer id) {
return serviceMetodoPago.buscarId(id);
}

@DeleteMapping("/metodos_pago/{id}")
public String elminar(@PathVariable Integer id) {
serviceMetodoPago.eliminar(id);
return "Registro Eliminado";
}
}
