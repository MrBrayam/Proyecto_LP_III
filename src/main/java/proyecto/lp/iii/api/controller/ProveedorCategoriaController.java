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

import proyecto.lp.iii.api.entity.ProveedorCategoria;
import proyecto.lp.iii.api.service.IProveedorCategoriaService;

@RestController
@RequestMapping("/api")
public class ProveedorCategoriaController {
@Autowired
private IProveedorCategoriaService serviceProveedorCategoria;

@GetMapping("/proveedor_categorias")
public List<ProveedorCategoria> buscarTodos() {
return serviceProveedorCategoria.buscarTodos();
}

@PostMapping("/proveedor_categorias")
public ProveedorCategoria guardar(@RequestBody ProveedorCategoria registro) {
serviceProveedorCategoria.guardar(registro);
return registro;
}

@PutMapping("/proveedor_categorias")
public ProveedorCategoria modificar(@RequestBody ProveedorCategoria registro) {
serviceProveedorCategoria.modificar(registro);
return registro;
}

@GetMapping("/proveedor_categorias/{id}")
public Optional<ProveedorCategoria> buscarId(@PathVariable("id") Integer id) {
return serviceProveedorCategoria.buscarId(id);
}

@DeleteMapping("/proveedor_categorias/{id}")
public String elminar(@PathVariable Integer id) {
serviceProveedorCategoria.eliminar(id);
return "Registro Eliminado";
}
}
