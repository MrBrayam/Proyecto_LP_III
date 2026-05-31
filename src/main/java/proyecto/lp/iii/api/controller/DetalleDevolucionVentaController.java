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

import proyecto.lp.iii.api.entity.DetalleDevolucionVenta;
import proyecto.lp.iii.api.service.IDetalleDevolucionVentaService;

@RestController
@RequestMapping("/api")
public class DetalleDevolucionVentaController {
	@Autowired
	private IDetalleDevolucionVentaService serviceDetalleDevolucionVenta;

	@GetMapping("/detalle_devolucion_venta")
	public List<DetalleDevolucionVenta> buscarTodos() {
		return serviceDetalleDevolucionVenta.buscarTodos();
	}

	@PostMapping("/detalle_devolucion_venta")
	public DetalleDevolucionVenta guardar(@RequestBody DetalleDevolucionVenta registro) {
		serviceDetalleDevolucionVenta.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_devolucion_venta")
	public DetalleDevolucionVenta modificar(@RequestBody DetalleDevolucionVenta registro) {
		serviceDetalleDevolucionVenta.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_devolucion_venta/{id}")
	public Optional<DetalleDevolucionVenta> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleDevolucionVenta.buscarId(id);
	}

	@DeleteMapping("/detalle_devolucion_venta/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleDevolucionVenta.eliminar(id);
		return "Registro Eliminado";
	}
}
