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

import proyecto.lp.iii.api.entity.PagoProveedor;
import proyecto.lp.iii.api.service.IPagoProveedorService;

@RestController
@RequestMapping("/api")
public class PagoProveedorController {
@Autowired
private IPagoProveedorService servicePagoProveedor;

@GetMapping("/pagos_proveedor")
public List<PagoProveedor> buscarTodos() {
return servicePagoProveedor.buscarTodos();
}

@PostMapping("/pagos_proveedor")
public PagoProveedor guardar(@RequestBody PagoProveedor registro) {
servicePagoProveedor.guardar(registro);
return registro;
}

@PutMapping("/pagos_proveedor")
public PagoProveedor modificar(@RequestBody PagoProveedor registro) {
servicePagoProveedor.modificar(registro);
return registro;
}

@GetMapping("/pagos_proveedor/{id}")
public Optional<PagoProveedor> buscarId(@PathVariable("id") Integer id) {
return servicePagoProveedor.buscarId(id);
}

@DeleteMapping("/pagos_proveedor/{id}")
public String elminar(@PathVariable Integer id) {
servicePagoProveedor.eliminar(id);
return "Registro Eliminado";
}
}
