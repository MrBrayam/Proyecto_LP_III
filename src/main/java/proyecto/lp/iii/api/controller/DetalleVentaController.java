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

import proyecto.lp.iii.api.entity.DetalleVenta;
import proyecto.lp.iii.api.service.IDetalleVentaService;

@RestController
@RequestMapping("/api")
public class DetalleVentaController {
	@Autowired
	private IDetalleVentaService serviceDetalleVenta;

	@GetMapping("/detalle_venta")
	public List<DetalleVenta> buscarTodos() {
		return serviceDetalleVenta.buscarTodos();
	}

	@PostMapping("/detalle_venta")
	public DetalleVenta guardar(@RequestBody DetalleVenta registro) {
		serviceDetalleVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_venta")
	public DetalleVenta modificar(@RequestBody DetalleVenta registro) {
		serviceDetalleVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_venta/{id}")
	public Optional<DetalleVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleVenta.buscarId(id);
	}

	@DeleteMapping("/detalle_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
