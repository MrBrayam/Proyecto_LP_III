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

import proyecto.lp.iii.api.entity.ConfiguracionTiendaOnline;
import proyecto.lp.iii.api.service.IConfiguracionTiendaOnlineService;

@RestController
@RequestMapping("/api")
public class ConfiguracionTiendaOnlineController {
	@Autowired
	private IConfiguracionTiendaOnlineService serviceConfiguracionTiendaOnline;

	@GetMapping("/configuracion_tienda_online")
	public List<ConfiguracionTiendaOnline> buscarTodos() {
		return serviceConfiguracionTiendaOnline.buscarTodos();
	}

	@PostMapping("/configuracion_tienda_online")
	public ConfiguracionTiendaOnline guardar(@RequestBody ConfiguracionTiendaOnline registro) {
		serviceConfiguracionTiendaOnline.guardar(registro);
		return registro;
	}

	@PutMapping("/configuracion_tienda_online")
	public ConfiguracionTiendaOnline modificar(@RequestBody ConfiguracionTiendaOnline registro) {
		serviceConfiguracionTiendaOnline.modificar(registro);
		return registro;
	}

	@GetMapping("/configuracion_tienda_online/{id}")
	public Optional<ConfiguracionTiendaOnline> buscarId(@PathVariable("id") Integer id) {
		return serviceConfiguracionTiendaOnline.buscarId(id);
	}

	@DeleteMapping("/configuracion_tienda_online/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceConfiguracionTiendaOnline.eliminar(id);
		return "Registro Eliminado";
	}
}
