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

import proyecto.lp.iii.api.entity.ServicioCita;
import proyecto.lp.iii.api.service.IServicioCitaService;

@RestController
@RequestMapping("/api")
public class ServicioCitaController {
	@Autowired
	private IServicioCitaService service;

	@GetMapping("/servicio_cita")
	public List<ServicioCita> buscarTodos() {
		return service.buscarTodos();
	}

	@PostMapping("/servicio_cita")
	public ServicioCita guardar(@RequestBody ServicioCita registro) {
		service.guardar(registro);
		return registro;
	}

	@PutMapping("/servicio_cita")
	public ServicioCita modificar(@RequestBody ServicioCita registro) {
		service.modificar(registro);
		return registro;
	}

	@GetMapping("/servicio_cita/{id}")
	public Optional<ServicioCita> buscarId(@PathVariable("id") Integer id) {
		return service.buscarId(id);
	}

	@DeleteMapping("/servicio_cita/{id}")
	public String elminar(@PathVariable Integer id) {
		service.eliminar(id);
		return "Registro Eliminado";
	}
}
