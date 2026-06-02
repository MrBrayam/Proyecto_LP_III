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

import proyecto.lp.iii.api.entity.PlanSuscripcion;
import proyecto.lp.iii.api.service.IPlanSuscripcionService;

@RestController
@RequestMapping("/api")
public class PlanSuscripcionController {
@Autowired
private IPlanSuscripcionService servicePlanSuscripcion;

@GetMapping("/planes_suscripcion")
public List<PlanSuscripcion> buscarTodos() {
return servicePlanSuscripcion.buscarTodos();
}

@PostMapping("/planes_suscripcion")
public PlanSuscripcion guardar(@RequestBody PlanSuscripcion registro) {
servicePlanSuscripcion.guardar(registro);
return registro;
}

@PutMapping("/planes_suscripcion")
public PlanSuscripcion modificar(@RequestBody PlanSuscripcion registro) {
servicePlanSuscripcion.modificar(registro);
return registro;
}

@GetMapping("/planes_suscripcion/{id}")
public Optional<PlanSuscripcion> buscarId(@PathVariable("id") Integer id) {
return servicePlanSuscripcion.buscarId(id);
}

@DeleteMapping("/planes_suscripcion/{id}")
public String elminar(@PathVariable Integer id) {
servicePlanSuscripcion.eliminar(id);
return "Registro Eliminado";
}
}
