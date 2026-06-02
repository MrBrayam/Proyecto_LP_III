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

import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.service.IServicioBellezaService;

@RestController
@RequestMapping("/api")
public class ServicioBellezaController {
@Autowired
private IServicioBellezaService serviceServicioBelleza;

@GetMapping("/servicios_belleza")
public List<ServicioBelleza> buscarTodos() {
return serviceServicioBelleza.buscarTodos();
}

@PostMapping("/servicios_belleza")
public ServicioBelleza guardar(@RequestBody ServicioBelleza registro) {
serviceServicioBelleza.guardar(registro);
return registro;
}

@PutMapping("/servicios_belleza")
public ServicioBelleza modificar(@RequestBody ServicioBelleza registro) {
serviceServicioBelleza.modificar(registro);
return registro;
}

@GetMapping("/servicios_belleza/{id}")
public Optional<ServicioBelleza> buscarId(@PathVariable("id") Integer id) {
return serviceServicioBelleza.buscarId(id);
}

@DeleteMapping("/servicios_belleza/{id}")
public String elminar(@PathVariable Integer id) {
serviceServicioBelleza.eliminar(id);
return "Registro Eliminado";
}
}
