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

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.service.IProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {
@Autowired
private IProductoService serviceProducto;

@GetMapping("/productos")
public List<Producto> buscarTodos() {
return serviceProducto.buscarTodos();
}

@PostMapping("/productos")
public Producto guardar(@RequestBody Producto registro) {
serviceProducto.guardar(registro);
return registro;
}

@PutMapping("/productos")
public Producto modificar(@RequestBody Producto registro) {
serviceProducto.modificar(registro);
return registro;
}

@GetMapping("/productos/{id}")
public Optional<Producto> buscarId(@PathVariable("id") Integer id) {
return serviceProducto.buscarId(id);
}

@DeleteMapping("/productos/{id}")
public String elminar(@PathVariable Integer id) {
serviceProducto.eliminar(id);
return "Registro Eliminado";
}
}
