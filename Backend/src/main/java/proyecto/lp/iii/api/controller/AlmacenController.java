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

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.entity.LoteInventario;
import proyecto.lp.iii.api.service.IAlmacenService;
import proyecto.lp.iii.api.service.ILoteInventarioService;

@RestController
@RequestMapping("/api")
public class AlmacenController {
	@Autowired
	private IAlmacenService serviceAlmacen;

	@Autowired
	private ILoteInventarioService serviceLoteInventario;

	private void calcularCapacidades(Almacen a) {
		if (a == null) return;
		List<LoteInventario> lotes = serviceLoteInventario.buscarTodos();
		int sumProducts = lotes.stream()
			.filter(l -> l.getId_almacenes() != null && l.getId_almacenes().getId_almacenes().equals(a.getId_almacenes())
					&& l.getEstado() != null && l.getEstado() == 1 && l.getCantidad_disponible() != null)
			.mapToInt(LoteInventario::getCantidad_disponible)
			.sum();
		a.setOcupado(sumProducts);
		int cap = a.getCapacidad() != null ? a.getCapacidad() : 0;
		a.setDisponible(Math.max(0, cap - sumProducts));
	}

	@GetMapping("/almacenes")
	public List<Almacen> buscarTodos() {
		List<Almacen> list = serviceAlmacen.buscarTodos();
		list.forEach(this::calcularCapacidades);
		return list;
	}

	@PostMapping("/almacenes")
	public Almacen guardar(@RequestBody Almacen registro) {
		serviceAlmacen.guardar(registro);
		calcularCapacidades(registro);
		return registro;
	}

	@PutMapping("/almacenes/{id}")
	public Almacen modificar(@PathVariable Integer id, @RequestBody Almacen registro) {
		registro.setId_almacenes(id);
		serviceAlmacen.modificar(registro);
		calcularCapacidades(registro);
		return registro;
	}

	@GetMapping("/almacenes/{id}")
	public Optional<Almacen> buscarId(@PathVariable("id") Integer id) {
		Optional<Almacen> opt = serviceAlmacen.buscarId(id);
		opt.ifPresent(this::calcularCapacidades);
		return opt;
	}

	@DeleteMapping("/almacenes/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceAlmacen.eliminar(id);
		return "Registro Eliminado";
	}
}
