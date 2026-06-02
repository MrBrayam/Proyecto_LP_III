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

import proyecto.lp.iii.api.entity.Repartidor;
import proyecto.lp.iii.api.service.IRepartidorService;

@RestController
@RequestMapping("/api")
public class RepartidorController {
@Autowired
private IRepartidorService serviceRepartidor;

@GetMapping("/repartidores")
public List<Repartidor> buscarTodos() {
return serviceRepartidor.buscarTodos();
}

@PostMapping("/repartidores")
public Repartidor guardar(@RequestBody Repartidor registro) {
serviceRepartidor.guardar(registro);
return registro;
}

@PutMapping("/repartidores")
public Repartidor modificar(@RequestBody Repartidor registro) {
serviceRepartidor.modificar(registro);
return registro;
}

@GetMapping("/repartidores/{id}")
public Optional<Repartidor> buscarId(@PathVariable("id") Integer id) {
return serviceRepartidor.buscarId(id);
}

@DeleteMapping("/repartidores/{id}")
public String elminar(@PathVariable Integer id) {
serviceRepartidor.eliminar(id);
return "Registro Eliminado";
}
}
