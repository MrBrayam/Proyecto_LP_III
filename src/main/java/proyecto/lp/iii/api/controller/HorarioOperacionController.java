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

import proyecto.lp.iii.api.entity.HorarioOperacion;
import proyecto.lp.iii.api.service.IHorarioOperacionService;

@RestController
@RequestMapping("/api")
public class HorarioOperacionController {
@Autowired
private IHorarioOperacionService serviceHorarioOperacion;

@GetMapping("/horarios_operacion")
public List<HorarioOperacion> buscarTodos() {
return serviceHorarioOperacion.buscarTodos();
}

@PostMapping("/horarios_operacion")
public HorarioOperacion guardar(@RequestBody HorarioOperacion registro) {
serviceHorarioOperacion.guardar(registro);
return registro;
}

@PutMapping("/horarios_operacion")
public HorarioOperacion modificar(@RequestBody HorarioOperacion registro) {
serviceHorarioOperacion.modificar(registro);
return registro;
}

@GetMapping("/horarios_operacion/{id}")
public Optional<HorarioOperacion> buscarId(@PathVariable("id") Integer id) {
return serviceHorarioOperacion.buscarId(id);
}

@DeleteMapping("/horarios_operacion/{id}")
public String elminar(@PathVariable Integer id) {
serviceHorarioOperacion.eliminar(id);
return "Registro Eliminado";
}
}
