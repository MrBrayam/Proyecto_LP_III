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

import proyecto.lp.iii.api.entity.DetalleOrdenCompra;
import proyecto.lp.iii.api.service.IDetalleOrdenCompraService;

@RestController
@RequestMapping("/api")
public class DetalleOrdenCompraController {
	@Autowired
	private IDetalleOrdenCompraService serviceDetalleOrdenCompra;

	@GetMapping("/detalle_orden_compra")
	public List<DetalleOrdenCompra> buscarTodos() {
		return serviceDetalleOrdenCompra.buscarTodos();
	}

	@PostMapping("/detalle_orden_compra")
	public DetalleOrdenCompra guardar(@RequestBody DetalleOrdenCompra registro) {
		serviceDetalleOrdenCompra.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_orden_compra")
	public DetalleOrdenCompra modificar(@RequestBody DetalleOrdenCompra registro) {
		serviceDetalleOrdenCompra.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_orden_compra/{id}")
	public Optional<DetalleOrdenCompra> buscarId(@PathVariable("id") Integer id) {
		return serviceDetalleOrdenCompra.buscarId(id);
	}

	@DeleteMapping("/detalle_orden_compra/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetalleOrdenCompra.eliminar(id);
		return "Registro Eliminado";
	}
}
