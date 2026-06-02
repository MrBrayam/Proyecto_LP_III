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

import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.service.ILoteInventarioService;

@RestController
@RequestMapping("/api")
public class LoteInventarioController {
@Autowired
private ILoteInventarioService serviceLoteInventario;

@GetMapping("/lotes_inventario")
public List<LoteInventario> buscarTodos() {
return serviceLoteInventario.buscarTodos();
}

@PostMapping("/lotes_inventario")
public LoteInventario guardar(@RequestBody LoteInventario registro) {
serviceLoteInventario.guardar(registro);
return registro;
}

@PutMapping("/lotes_inventario")
public LoteInventario modificar(@RequestBody LoteInventario registro) {
serviceLoteInventario.modificar(registro);
return registro;
}

@GetMapping("/lotes_inventario/{id}")
public Optional<LoteInventario> buscarId(@PathVariable("id") Integer id) {
return serviceLoteInventario.buscarId(id);
}

@DeleteMapping("/lotes_inventario/{id}")
public String elminar(@PathVariable Integer id) {
serviceLoteInventario.eliminar(id);
return "Registro Eliminado";
}
}
