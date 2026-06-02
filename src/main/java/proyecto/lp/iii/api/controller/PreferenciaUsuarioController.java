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

import proyecto.lp.iii.api.entity.PreferenciaUsuario;
import proyecto.lp.iii.api.service.IPreferenciaUsuarioService;

@RestController
@RequestMapping("/api")
public class PreferenciaUsuarioController {
@Autowired
private IPreferenciaUsuarioService servicePreferenciaUsuario;

@GetMapping("/preferencias_usuario")
public List<PreferenciaUsuario> buscarTodos() {
return servicePreferenciaUsuario.buscarTodos();
}

@PostMapping("/preferencias_usuario")
public PreferenciaUsuario guardar(@RequestBody PreferenciaUsuario registro) {
servicePreferenciaUsuario.guardar(registro);
return registro;
}

@PutMapping("/preferencias_usuario")
public PreferenciaUsuario modificar(@RequestBody PreferenciaUsuario registro) {
servicePreferenciaUsuario.modificar(registro);
return registro;
}

@GetMapping("/preferencias_usuario/{id}")
public Optional<PreferenciaUsuario> buscarId(@PathVariable("id") Integer id) {
return servicePreferenciaUsuario.buscarId(id);
}

@DeleteMapping("/preferencias_usuario/{id}")
public String elminar(@PathVariable Integer id) {
servicePreferenciaUsuario.eliminar(id);
return "Registro Eliminado";
}
}
