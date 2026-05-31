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

import proyecto.lp.iii.api.entity.DevolucionVenta;
import proyecto.lp.iii.api.service.IDevolucionVentaService;

@RestController
@RequestMapping("/api")
public class DevolucionVentaController {
	@Autowired
	private IDevolucionVentaService serviceDevolucionVenta;

	@GetMapping("/devoluciones_venta")
	public List<DevolucionVenta> buscarTodos() {
		return serviceDevolucionVenta.buscarTodos();
	}

	@PostMapping("/devoluciones_venta")
	public DevolucionVenta guardar(@RequestBody DevolucionVenta registro) {
		serviceDevolucionVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/devoluciones_venta")
	public DevolucionVenta modificar(@RequestBody DevolucionVenta registro) {
		serviceDevolucionVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/devoluciones_venta/{id}")
	public Optional<DevolucionVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDevolucionVenta.buscarId(id);
	}

	@DeleteMapping("/devoluciones_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDevolucionVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
