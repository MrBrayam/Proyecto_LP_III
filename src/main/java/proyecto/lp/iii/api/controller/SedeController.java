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

import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.service.ISedeService;

@RestController
@RequestMapping("/api")
public class SedeController {
@Autowired
private ISedeService serviceSede;

@GetMapping("/sedes")
public List<Sede> buscarTodos() {
return serviceSede.buscarTodos();
}

@PostMapping("/sedes")
public Sede guardar(@RequestBody Sede registro) {
serviceSede.guardar(registro);
return registro;
}

@PutMapping("/sedes")
public Sede modificar(@RequestBody Sede registro) {
serviceSede.modificar(registro);
return registro;
}

@GetMapping("/sedes/{id}")
public Optional<Sede> buscarId(@PathVariable("id") Integer id) {
return serviceSede.buscarId(id);
}

@DeleteMapping("/sedes/{id}")
public String elminar(@PathVariable Integer id) {
serviceSede.eliminar(id);
return "Registro Eliminado";
}
}
