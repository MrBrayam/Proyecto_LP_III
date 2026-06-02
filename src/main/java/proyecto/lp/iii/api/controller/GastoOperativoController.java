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

import proyecto.lp.iii.api.entity.GastoOperativo;
import proyecto.lp.iii.api.service.IGastoOperativoService;

@RestController
@RequestMapping("/api")
public class GastoOperativoController {
@Autowired
private IGastoOperativoService serviceGastoOperativo;

@GetMapping("/gastos_operativos")
public List<GastoOperativo> buscarTodos() {
return serviceGastoOperativo.buscarTodos();
}

@PostMapping("/gastos_operativos")
public GastoOperativo guardar(@RequestBody GastoOperativo registro) {
serviceGastoOperativo.guardar(registro);
return registro;
}

@PutMapping("/gastos_operativos")
public GastoOperativo modificar(@RequestBody GastoOperativo registro) {
serviceGastoOperativo.modificar(registro);
return registro;
}

@GetMapping("/gastos_operativos/{id}")
public Optional<GastoOperativo> buscarId(@PathVariable("id") Integer id) {
return serviceGastoOperativo.buscarId(id);
}

@DeleteMapping("/gastos_operativos/{id}")
public String elminar(@PathVariable Integer id) {
serviceGastoOperativo.eliminar(id);
return "Registro Eliminado";
}
}
