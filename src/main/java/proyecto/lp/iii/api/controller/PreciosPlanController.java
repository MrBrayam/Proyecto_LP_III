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

import proyecto.lp.iii.api.entity.PreciosPlan;
import proyecto.lp.iii.api.service.IPreciosPlanService;

@RestController
@RequestMapping("/api")
public class PreciosPlanController {
@Autowired
private IPreciosPlanService servicePreciosPlan;

@GetMapping("/precios_plan")
public List<PreciosPlan> buscarTodos() {
return servicePreciosPlan.buscarTodos();
}

@PostMapping("/precios_plan")
public PreciosPlan guardar(@RequestBody PreciosPlan registro) {
servicePreciosPlan.guardar(registro);
return registro;
}

@PutMapping("/precios_plan")
public PreciosPlan modificar(@RequestBody PreciosPlan registro) {
servicePreciosPlan.modificar(registro);
return registro;
}

@GetMapping("/precios_plan/{id}")
public Optional<PreciosPlan> buscarId(@PathVariable("id") Integer id) {
return servicePreciosPlan.buscarId(id);
}

@DeleteMapping("/precios_plan/{id}")
public String elminar(@PathVariable Integer id) {
servicePreciosPlan.eliminar(id);
return "Registro Eliminado";
}
}
