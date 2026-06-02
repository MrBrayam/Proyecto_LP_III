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

import proyecto.lp.iii.api.entity.Proveedor;
import proyecto.lp.iii.api.service.IProveedorService;

@RestController
@RequestMapping("/api")
public class ProveedorController {
@Autowired
private IProveedorService serviceProveedor;

@GetMapping("/proveedores")
public List<Proveedor> buscarTodos() {
return serviceProveedor.buscarTodos();
}

@PostMapping("/proveedores")
public Proveedor guardar(@RequestBody Proveedor registro) {
serviceProveedor.guardar(registro);
return registro;
}

@PutMapping("/proveedores")
public Proveedor modificar(@RequestBody Proveedor registro) {
serviceProveedor.modificar(registro);
return registro;
}

@GetMapping("/proveedores/{id}")
public Optional<Proveedor> buscarId(@PathVariable("id") Integer id) {
return serviceProveedor.buscarId(id);
}

@DeleteMapping("/proveedores/{id}")
public String elminar(@PathVariable Integer id) {
serviceProveedor.eliminar(id);
return "Registro Eliminado";
}
}
