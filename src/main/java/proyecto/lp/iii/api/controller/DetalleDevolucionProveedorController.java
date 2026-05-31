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

import proyecto.lp.iii.api.entity.DetalleDevolucionProveedor;
import proyecto.lp.iii.api.service.IDetalleDevolucionProveedorService;

@RestController
@RequestMapping("/api")
public class DetalleDevolucionProveedorController {
	@Autowired
	private IDetalleDevolucionProveedorService serviceDetalleDevolucionProveedor;

	@GetMapping("/detalle_devolucion_proveedor")
	public List<DetalleDevolucionProveedor> buscarTodos() {
		return serviceDetalleDevolucionProveedor.buscarTodos();
	}

	@PostMapping("/detalle_devolucion_proveedor")
	public DetalleDevolucionProveedor guardar(@RequestBody DetalleDevolucionProveedor registro) {
		serviceDetalleDevolucionProveedor.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_devolucion_proveedor")
	public DetalleDevolucionProveedor modificar(@RequestBody DetalleDevolucionProveedor registro) {
		serviceDetalleDevolucionProveedor.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_devolucion_proveedor/{id}")
	public Optional<DetalleDevolucionProveedor> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleDevolucionProveedor.buscarId(id);
	}

	@DeleteMapping("/detalle_devolucion_proveedor/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleDevolucionProveedor.eliminar(id);
		return "Registro Eliminado";
	}
}
