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

import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.service.ISuscripcionService;

@RestController
@RequestMapping("/api")
public class SuscripcionController {
@Autowired
private ISuscripcionService serviceSuscripcion;

@GetMapping("/suscripciones")
public List<Suscripcion> buscarTodos() {
return serviceSuscripcion.buscarTodos();
}

@PostMapping("/suscripciones")
public Suscripcion guardar(@RequestBody Suscripcion registro) {
serviceSuscripcion.guardar(registro);
return registro;
}

@PutMapping("/suscripciones")
public Suscripcion modificar(@RequestBody Suscripcion registro) {
serviceSuscripcion.modificar(registro);
return registro;
}

@GetMapping("/suscripciones/{id}")
public Optional<Suscripcion> buscarId(@PathVariable("id") Integer id) {
return serviceSuscripcion.buscarId(id);
}

@DeleteMapping("/suscripciones/{id}")
public String elminar(@PathVariable Integer id) {
serviceSuscripcion.eliminar(id);
return "Registro Eliminado";
}
}
