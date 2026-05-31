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

import proyecto.lp.iii.api.entity.DevolucionProveedor;
import proyecto.lp.iii.api.service.IDevolucionProveedorService;

@RestController
@RequestMapping("/api")
public class DevolucionProveedorController {
	@Autowired
	private IDevolucionProveedorService serviceDevolucionProveedor;

	@GetMapping("/devoluciones_proveedor")
	public List<DevolucionProveedor> buscarTodos() {
		return serviceDevolucionProveedor.buscarTodos();
	}

	@PostMapping("/devoluciones_proveedor")
	public DevolucionProveedor guardar(@RequestBody DevolucionProveedor registro) {
		serviceDevolucionProveedor.guardar(registro);
		return registro;
	}

	@PutMapping("/devoluciones_proveedor")
	public DevolucionProveedor modificar(@RequestBody DevolucionProveedor registro) {
		serviceDevolucionProveedor.modificar(registro);
		return registro;
	}

	@GetMapping("/devoluciones_proveedor/{id}")
	public Optional<DevolucionProveedor> buscarId(@PathVariable("id") Integer id) {
		return serviceDevolucionProveedor.buscarId(id);
	}

	@DeleteMapping("/devoluciones_proveedor/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDevolucionProveedor.eliminar(id);
		return "Registro Eliminado";
	}
}
