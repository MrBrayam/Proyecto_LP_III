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

import proyecto.lp.iii.api.entity.Reclamo;
import proyecto.lp.iii.api.service.IReclamoService;

@RestController
@RequestMapping("/api")
public class ReclamoController {
@Autowired
private IReclamoService serviceReclamo;

@GetMapping("/reclamos")
public List<Reclamo> buscarTodos() {
return serviceReclamo.buscarTodos();
}

@PostMapping("/reclamos")
public Reclamo guardar(@RequestBody Reclamo registro) {
serviceReclamo.guardar(registro);
return registro;
}

@PutMapping("/reclamos")
public Reclamo modificar(@RequestBody Reclamo registro) {
serviceReclamo.modificar(registro);
return registro;
}

@GetMapping("/reclamos/{id}")
public Optional<Reclamo> buscarId(@PathVariable("id") Integer id) {
return serviceReclamo.buscarId(id);
}

@DeleteMapping("/reclamos/{id}")
public String elminar(@PathVariable Integer id) {
serviceReclamo.eliminar(id);
return "Registro Eliminado";
}
}
