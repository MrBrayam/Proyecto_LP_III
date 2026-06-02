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

import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.service.ISesionCajaService;

@RestController
@RequestMapping("/api")
public class SesionCajaController {
@Autowired
private ISesionCajaService serviceSesionCaja;

@GetMapping("/sesiones_caja")
public List<SesionCaja> buscarTodos() {
return serviceSesionCaja.buscarTodos();
}

@PostMapping("/sesiones_caja")
public SesionCaja guardar(@RequestBody SesionCaja registro) {
serviceSesionCaja.guardar(registro);
return registro;
}

@PutMapping("/sesiones_caja")
public SesionCaja modificar(@RequestBody SesionCaja registro) {
serviceSesionCaja.modificar(registro);
return registro;
}

@GetMapping("/sesiones_caja/{id}")
public Optional<SesionCaja> buscarId(@PathVariable("id") Integer id) {
return serviceSesionCaja.buscarId(id);
}

@DeleteMapping("/sesiones_caja/{id}")
public String elminar(@PathVariable Integer id) {
serviceSesionCaja.eliminar(id);
return "Registro Eliminado";
}
}
