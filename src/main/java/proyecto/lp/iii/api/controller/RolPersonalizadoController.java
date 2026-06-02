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

import proyecto.lp.iii.api.entity.RolPersonalizado;
import proyecto.lp.iii.api.service.IRolPersonalizadoService;

@RestController
@RequestMapping("/api")
public class RolPersonalizadoController {
@Autowired
private IRolPersonalizadoService serviceRolPersonalizado;

@GetMapping("/roles_personalizados")
public List<RolPersonalizado> buscarTodos() {
return serviceRolPersonalizado.buscarTodos();
}

@PostMapping("/roles_personalizados")
public RolPersonalizado guardar(@RequestBody RolPersonalizado registro) {
serviceRolPersonalizado.guardar(registro);
return registro;
}

@PutMapping("/roles_personalizados")
public RolPersonalizado modificar(@RequestBody RolPersonalizado registro) {
serviceRolPersonalizado.modificar(registro);
return registro;
}

@GetMapping("/roles_personalizados/{id}")
public Optional<RolPersonalizado> buscarId(@PathVariable("id") Integer id) {
return serviceRolPersonalizado.buscarId(id);
}

@DeleteMapping("/roles_personalizados/{id}")
public String elminar(@PathVariable Integer id) {
serviceRolPersonalizado.eliminar(id);
return "Registro Eliminado";
}
}
