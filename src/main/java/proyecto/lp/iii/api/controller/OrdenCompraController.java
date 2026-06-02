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

import proyecto.lp.iii.api.entity.OrdenCompra;
import proyecto.lp.iii.api.service.IOrdenCompraService;

@RestController
@RequestMapping("/api")
public class OrdenCompraController {
@Autowired
private IOrdenCompraService serviceOrdenCompra;

@GetMapping("/ordenes_compra")
public List<OrdenCompra> buscarTodos() {
return serviceOrdenCompra.buscarTodos();
}

@PostMapping("/ordenes_compra")
public OrdenCompra guardar(@RequestBody OrdenCompra registro) {
serviceOrdenCompra.guardar(registro);
return registro;
}

@PutMapping("/ordenes_compra")
public OrdenCompra modificar(@RequestBody OrdenCompra registro) {
serviceOrdenCompra.modificar(registro);
return registro;
}

@GetMapping("/ordenes_compra/{id}")
public Optional<OrdenCompra> buscarId(@PathVariable("id") Integer id) {
return serviceOrdenCompra.buscarId(id);
}

@DeleteMapping("/ordenes_compra/{id}")
public String elminar(@PathVariable Integer id) {
serviceOrdenCompra.eliminar(id);
return "Registro Eliminado";
}
}
