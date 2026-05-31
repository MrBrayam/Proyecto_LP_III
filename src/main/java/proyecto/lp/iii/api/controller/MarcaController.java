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

import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.service.IMarcaService;

@RestController
@RequestMapping("/api")
public class MarcaController {
	@Autowired
	private IMarcaService serviceMarca;

	@GetMapping("/marcas")
	public List<Marca> buscarTodos() {
		return serviceMarca.buscarTodos();
	}

	@PostMapping("/marcas")
	public Marca guardar(@RequestBody Marca registro) {
		serviceMarca.guardar(registro);
		return registro;
	}

	@PutMapping("/marcas")
	public Marca modificar(@RequestBody Marca registro) {
		serviceMarca.modificar(registro);
		return registro;
	}

	@GetMapping("/marcas/{id}")
	public Optional<Marca> buscarId(@PathVariable("id") Integer id) {
		return serviceMarca.buscarId(id);
	}

	@DeleteMapping("/marcas/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceMarca.eliminar(id);
		return "Registro Eliminado";
	}
}
