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

import org.springframework.transaction.annotation.Transactional;
import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.entity.MovimientoInventario;
import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.service.ILoteInventarioService;
import proyecto.lp.iii.api.service.IMovimientoInventarioService;
import proyecto.lp.iii.api.service.IProductoService;

@RestController
@RequestMapping("/api")
public class LoteInventarioController {
	@Autowired
	private ILoteInventarioService serviceLoteInventario;

	@Autowired
	private IProductoService serviceProducto;

	@Autowired
	private IMovimientoInventarioService serviceMovimientoInventario;

	private void actualizarStockProducto(Producto prod) {
		if (prod == null) return;
		List<LoteInventario> lotes = serviceLoteInventario.buscarTodos();
		int sumStock = lotes.stream()
			.filter(l -> l.getId_productos() != null && l.getId_productos().getId_productos().equals(prod.getId_productos())
					&& l.getEstado() != null && l.getEstado() == 1 && l.getCantidad_disponible() != null)
			.mapToInt(LoteInventario::getCantidad_disponible)
			.sum();
		prod.setStock_actual(sumStock);
		serviceProducto.modificar(prod);
	}

	@GetMapping("/lotes_inventario")
	public List<LoteInventario> buscarTodos() {
		return serviceLoteInventario.buscarTodos();
	}

	@PostMapping("/lotes_inventario")
	@Transactional
	public LoteInventario guardar(@RequestBody LoteInventario registro) {
		if (registro.getCantidad_disponible() == null) {
			registro.setCantidad_disponible(registro.getCantidad());
		}
		LoteInventario saved = serviceLoteInventario.guardar(registro);
		
		// 1. Update product stock
		actualizarStockProducto(saved.getId_productos());

		// 2. Register inventory movement
		MovimientoInventario mov = new MovimientoInventario();
		mov.setId_lotes_inventario(saved);
		mov.setTipo_movimiento("entrada");
		mov.setCantidad(saved.getCantidad());
		mov.setMotivo("Ingreso de lote inicial");
		mov.setReferencia_documento(saved.getNumero_lote());
		serviceMovimientoInventario.guardar(mov);

		return saved;
	}

	@PutMapping("/lotes_inventario/{id}")
	@Transactional
	public LoteInventario modificar(@PathVariable Integer id, @RequestBody LoteInventario registro) {
		registro.setId_lotes_inventario(id);
		
		LoteInventario original = serviceLoteInventario.buscarId(id).orElse(null);
		int delta = 0;
		if (original != null && registro.getCantidad_disponible() != null && original.getCantidad_disponible() != null) {
			delta = registro.getCantidad_disponible() - original.getCantidad_disponible();
		}

		serviceLoteInventario.modificar(registro);
		
		// 1. Update product stock
		actualizarStockProducto(registro.getId_productos());

		// 2. Register inventory movement if there's a difference
		if (delta != 0) {
			MovimientoInventario mov = new MovimientoInventario();
			mov.setId_lotes_inventario(registro);
			mov.setTipo_movimiento(delta > 0 ? "entrada" : "salida");
			mov.setCantidad(Math.abs(delta));
			mov.setMotivo("Ajuste de stock del lote");
			mov.setReferencia_documento(registro.getNumero_lote());
			serviceMovimientoInventario.guardar(mov);
		}

		return registro;
	}

	@GetMapping("/lotes_inventario/{id}")
	public Optional<LoteInventario> buscarId(@PathVariable("id") Integer id) {
		return serviceLoteInventario.buscarId(id);
	}

	@DeleteMapping("/lotes_inventario/{id}")
	@Transactional
	public String elminar(@PathVariable Integer id) {
		LoteInventario original = serviceLoteInventario.buscarId(id).orElse(null);
		serviceLoteInventario.eliminar(id);
		if (original != null) {
			actualizarStockProducto(original.getId_productos());
		}
		return "Registro Eliminado";
	}
}
