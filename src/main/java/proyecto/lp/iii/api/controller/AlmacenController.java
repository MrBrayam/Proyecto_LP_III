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
import proyecto.lp.iii.api.service.IAlmacenService;

@RestController
@RequestMapping("/api")
public class AlmacenController {
	@Autowired
	private IAlmacenService serviceAlmacen;

	@GetMapping("/almacenes")
	public List<Almacen> buscarTodos() {
		return serviceAlmacen.buscarTodos();
	}

	@PostMapping("/almacenes")
	public Almacen guardar(@RequestBody Almacen registro) {
		serviceAlmacen.guardar(registro);
		return registro;
	}

	@PutMapping("/almacenes")
	public Almacen modificar(@RequestBody Almacen registro) {
		serviceAlmacen.modificar(registro);
		return registro;
	}

	@GetMapping("/almacenes/{id}")
	public Optional<Almacen> buscarId(@PathVariable("id") Integer id) {
		return serviceAlmacen.buscarId(id);
	}

	@DeleteMapping("/almacenes/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceAlmacen.eliminar(id);
		return "Registro Eliminado";
	}
}
