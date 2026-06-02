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

import proyecto.lp.iii.api.entity.Promocion;
import proyecto.lp.iii.api.service.IPromocionService;

@RestController
@RequestMapping("/api")
public class PromocionController {
@Autowired
private IPromocionService servicePromocion;

@GetMapping("/promociones")
public List<Promocion> buscarTodos() {
return servicePromocion.buscarTodos();
}

@PostMapping("/promociones")
public Promocion guardar(@RequestBody Promocion registro) {
servicePromocion.guardar(registro);
return registro;
}

@PutMapping("/promociones")
public Promocion modificar(@RequestBody Promocion registro) {
servicePromocion.modificar(registro);
return registro;
}

@GetMapping("/promociones/{id}")
public Optional<Promocion> buscarId(@PathVariable("id") Integer id) {
return servicePromocion.buscarId(id);
}

@DeleteMapping("/promociones/{id}")
public String elminar(@PathVariable Integer id) {
servicePromocion.eliminar(id);
return "Registro Eliminado";
}
}
