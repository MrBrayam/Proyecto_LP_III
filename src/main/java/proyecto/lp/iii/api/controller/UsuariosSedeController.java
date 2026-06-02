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

import proyecto.lp.iii.api.entity.UsuarioSede;
import proyecto.lp.iii.api.service.IUsuarioSedeService;

@RestController
@RequestMapping("/api")
public class UsuariosSedeController {
@Autowired
private IUsuarioSedeService serviceUsuarioSede;

@GetMapping("/usuario_sedes")
public List<UsuarioSede> buscarTodos() {
return serviceUsuarioSede.buscarTodos();
}

@PostMapping("/usuario_sedes")
public UsuarioSede guardar(@RequestBody UsuarioSede registro) {
serviceUsuarioSede.guardar(registro);
return registro;
}

@PutMapping("/usuario_sedes")
public UsuarioSede modificar(@RequestBody UsuarioSede registro) {
serviceUsuarioSede.modificar(registro);
return registro;
}

@GetMapping("/usuario_sedes/{id}")
public Optional<UsuarioSede> buscarId(@PathVariable("id") Integer id) {
return serviceUsuarioSede.buscarId(id);
}

@DeleteMapping("/usuario_sedes/{id}")
public String elminar(@PathVariable Integer id) {
serviceUsuarioSede.eliminar(id);
return "Registro Eliminado";
}
}
