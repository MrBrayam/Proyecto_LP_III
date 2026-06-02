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

import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.service.IPermisoRolService;

@RestController
@RequestMapping("/api")
public class PermisoRolController {
@Autowired
private IPermisoRolService servicePermisoRol;

@GetMapping("/permisos_rol")
public List<PermisoRol> buscarTodos() {
return servicePermisoRol.buscarTodos();
}

@PostMapping("/permisos_rol")
public PermisoRol guardar(@RequestBody PermisoRol registro) {
servicePermisoRol.guardar(registro);
return registro;
}

@PutMapping("/permisos_rol")
public PermisoRol modificar(@RequestBody PermisoRol registro) {
servicePermisoRol.modificar(registro);
return registro;
}

@GetMapping("/permisos_rol/{id}")
public Optional<PermisoRol> buscarId(@PathVariable("id") Integer id) {
return servicePermisoRol.buscarId(id);
}

@DeleteMapping("/permisos_rol/{id}")
public String elminar(@PathVariable Integer id) {
servicePermisoRol.eliminar(id);
return "Registro Eliminado";
}
}
