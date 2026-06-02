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

import proyecto.lp.iii.api.entity.Notificacion;
import proyecto.lp.iii.api.service.INotificacionService;

@RestController
@RequestMapping("/api")
public class NotificacionController {
@Autowired
private INotificacionService serviceNotificacion;

@GetMapping("/notificaciones")
public List<Notificacion> buscarTodos() {
return serviceNotificacion.buscarTodos();
}

@PostMapping("/notificaciones")
public Notificacion guardar(@RequestBody Notificacion registro) {
serviceNotificacion.guardar(registro);
return registro;
}

@PutMapping("/notificaciones")
public Notificacion modificar(@RequestBody Notificacion registro) {
serviceNotificacion.modificar(registro);
return registro;
}

@GetMapping("/notificaciones/{id}")
public Optional<Notificacion> buscarId(@PathVariable("id") Integer id) {
return serviceNotificacion.buscarId(id);
}

@DeleteMapping("/notificaciones/{id}")
public String elminar(@PathVariable Integer id) {
serviceNotificacion.eliminar(id);
return "Registro Eliminado";
}
}
