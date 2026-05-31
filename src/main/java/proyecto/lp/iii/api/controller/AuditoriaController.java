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

import proyecto.lp.iii.api.entity.Auditoria;
import proyecto.lp.iii.api.service.IAuditoriaService;

@RestController
@RequestMapping("/api")
public class AuditoriaController {
	@Autowired
	private IAuditoriaService serviceAuditoria;

	@GetMapping("/auditoria")
	public List<Auditoria> buscarTodos() {
		return serviceAuditoria.buscarTodos();
	}

	@PostMapping("/auditoria")
	public Auditoria guardar(@RequestBody Auditoria registro) {
		serviceAuditoria.guardar(registro);
		return registro;
	}

	@PutMapping("/auditoria")
	public Auditoria modificar(@RequestBody Auditoria registro) {
		serviceAuditoria.modificar(registro);
		return registro;
	}

	@GetMapping("/auditoria/{id}")
	public Optional<Auditoria> buscarId(@PathVariable("id") Integer id) {
		return serviceAuditoria.buscarId(id);
	}

	@DeleteMapping("/auditoria/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceAuditoria.eliminar(id);
		return "Registro Eliminado";
	}
}
