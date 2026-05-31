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

import proyecto.lp.iii.api.entity.ConfiguracionGlobal;
import proyecto.lp.iii.api.service.IConfiguracionGlobalService;

@RestController
@RequestMapping("/api")
public class ConfiguracionGlobalController {
	@Autowired
	private IConfiguracionGlobalService serviceConfiguracionGlobal;

	@GetMapping("/configuracion_global")
	public List<ConfiguracionGlobal> buscarTodos() {
		return serviceConfiguracionGlobal.buscarTodos();
	}

	@PostMapping("/configuracion_global")
	public ConfiguracionGlobal guardar(@RequestBody ConfiguracionGlobal registro) {
		serviceConfiguracionGlobal.guardar(registro);
		return registro;
	}

	@PutMapping("/configuracion_global")
	public ConfiguracionGlobal modificar(@RequestBody ConfiguracionGlobal registro) {
		serviceConfiguracionGlobal.modificar(registro);
		return registro;
	}

	@GetMapping("/configuracion_global/{id}")
	public Optional<ConfiguracionGlobal> buscarId(@PathVariable("id") Integer id) {
		return serviceConfiguracionGlobal.buscarId(id);
	}

	@DeleteMapping("/configuracion_global/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceConfiguracionGlobal.eliminar(id);
		return "Registro Eliminado";
	}
}
