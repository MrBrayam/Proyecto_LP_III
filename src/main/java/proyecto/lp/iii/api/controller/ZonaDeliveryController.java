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

import proyecto.lp.iii.api.entity.ZonaDelivery;
import proyecto.lp.iii.api.service.IZonaDeliveryService;

@RestController
@RequestMapping("/api")
public class ZonaDeliveryController {
@Autowired
private IZonaDeliveryService serviceZonaDelivery;

@GetMapping("/zonas_delivery")
public List<ZonaDelivery> buscarTodos() {
return serviceZonaDelivery.buscarTodos();
}

@PostMapping("/zonas_delivery")
public ZonaDelivery guardar(@RequestBody ZonaDelivery registro) {
serviceZonaDelivery.guardar(registro);
return registro;
}

@PutMapping("/zonas_delivery")
public ZonaDelivery modificar(@RequestBody ZonaDelivery registro) {
serviceZonaDelivery.modificar(registro);
return registro;
}

@GetMapping("/zonas_delivery/{id}")
public Optional<ZonaDelivery> buscarId(@PathVariable("id") Integer id) {
return serviceZonaDelivery.buscarId(id);
}

@DeleteMapping("/zonas_delivery/{id}")
public String elminar(@PathVariable Integer id) {
serviceZonaDelivery.eliminar(id);
return "Registro Eliminado";
}
}
