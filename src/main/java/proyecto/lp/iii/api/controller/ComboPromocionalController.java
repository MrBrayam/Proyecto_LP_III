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

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.service.IComboPromocionalService;

@RestController
@RequestMapping("/api")
public class ComboPromocionalController {
	@Autowired
	private IComboPromocionalService serviceComboPromocional;

	@GetMapping("/combos_promocionales")
	public List<ComboPromocional> buscarTodos() {
		return serviceComboPromocional.buscarTodos();
	}

	@PostMapping("/combos_promocionales")
	public ComboPromocional guardar(@RequestBody ComboPromocional registro) {
		serviceComboPromocional.guardar(registro);
		return registro;
	}

	@PutMapping("/combos_promocionales")
	public ComboPromocional modificar(@RequestBody ComboPromocional registro) {
		serviceComboPromocional.modificar(registro);
		return registro;
	}

	@GetMapping("/combos_promocionales/{id}")
	public Optional<ComboPromocional> buscarId(@PathVariable("id") Integer id) {
		return serviceComboPromocional.buscarId(id);
	}

	@DeleteMapping("/combos_promocionales/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceComboPromocional.eliminar(id);
		return "Registro Eliminado";
	}
}
