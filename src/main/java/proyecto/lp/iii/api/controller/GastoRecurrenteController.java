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

import proyecto.lp.iii.api.entity.GastoRecurrente;
import proyecto.lp.iii.api.service.IGastoRecurrenteService;

@RestController
@RequestMapping("/api")
public class GastoRecurrenteController {
@Autowired
private IGastoRecurrenteService serviceGastoRecurrente;

@GetMapping("/gastos_recurrentes")
public List<GastoRecurrente> buscarTodos() {
return serviceGastoRecurrente.buscarTodos();
}

@PostMapping("/gastos_recurrentes")
public GastoRecurrente guardar(@RequestBody GastoRecurrente registro) {
serviceGastoRecurrente.guardar(registro);
return registro;
}

@PutMapping("/gastos_recurrentes")
public GastoRecurrente modificar(@RequestBody GastoRecurrente registro) {
serviceGastoRecurrente.modificar(registro);
return registro;
}

@GetMapping("/gastos_recurrentes/{id}")
public Optional<GastoRecurrente> buscarId(@PathVariable("id") Integer id) {
return serviceGastoRecurrente.buscarId(id);
}

@DeleteMapping("/gastos_recurrentes/{id}")
public String elminar(@PathVariable Integer id) {
serviceGastoRecurrente.eliminar(id);
return "Registro Eliminado";
}
}
