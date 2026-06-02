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

import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.service.IMovimientoInventarioService;

@RestController
@RequestMapping("/api")
public class MovimientoInventarioController {
@Autowired
private IMovimientoInventarioService serviceMovimientoInventario;

@GetMapping("/movimientos_inventario")
public List<MovimientoInventario> buscarTodos() {
return serviceMovimientoInventario.buscarTodos();
}

@PostMapping("/movimientos_inventario")
public MovimientoInventario guardar(@RequestBody MovimientoInventario registro) {
serviceMovimientoInventario.guardar(registro);
return registro;
}

@PutMapping("/movimientos_inventario")
public MovimientoInventario modificar(@RequestBody MovimientoInventario registro) {
serviceMovimientoInventario.modificar(registro);
return registro;
}

@GetMapping("/movimientos_inventario/{id}")
public Optional<MovimientoInventario> buscarId(@PathVariable("id") Integer id) {
return serviceMovimientoInventario.buscarId(id);
}

@DeleteMapping("/movimientos_inventario/{id}")
public String elminar(@PathVariable Integer id) {
serviceMovimientoInventario.eliminar(id);
return "Registro Eliminado";
}
}
