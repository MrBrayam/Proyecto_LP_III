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

import proyecto.lp.iii.api.entity.DetallePedido;
import proyecto.lp.iii.api.service.IDetallePedidoService;

@RestController
@RequestMapping("/api")
public class DetallePedidoController {
	@Autowired
	private IDetallePedidoService serviceDetallePedido;

	@GetMapping("/detalle_pedido")
	public List<DetallePedido> buscarTodos() {
		return serviceDetallePedido.buscarTodos();
	}

	@PostMapping("/detalle_pedido")
	public DetallePedido guardar(@RequestBody DetallePedido registro) {
		serviceDetallePedido.guardar(registro);
		return registro;
	}

	@PutMapping("/detalle_pedido")
	public DetallePedido modificar(@RequestBody DetallePedido registro) {
		serviceDetallePedido.modificar(registro);
		return registro;
	}

	@GetMapping("/detalle_pedido/{id}")
	public Optional<DetallePedido> buscarId(@PathVariable("id") Integer id) {
		return serviceDetallePedido.buscarId(id);
	}

	@DeleteMapping("/detalle_pedido/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceDetallePedido.eliminar(id);
		return "Registro Eliminado";
	}
}
