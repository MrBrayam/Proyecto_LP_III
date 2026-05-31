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

import proyecto.lp.iii.api.entity.CajaChica;
import proyecto.lp.iii.api.service.ICajaChicaService;

@RestController
@RequestMapping("/api")
public class CajaChicaController {
	@Autowired
	private ICajaChicaService serviceCajaChica;

	@GetMapping("/caja_chica")
	public List<CajaChica> buscarTodos() {
		return serviceCajaChica.buscarTodos();
	}

	@PostMapping("/caja_chica")
	public CajaChica guardar(@RequestBody CajaChica registro) {
		serviceCajaChica.guardar(registro);
		return registro;
	}

	@PutMapping("/caja_chica")
	public CajaChica modificar(@RequestBody CajaChica registro) {
		serviceCajaChica.modificar(registro);
		return registro;
	}

	@GetMapping("/caja_chica/{id}")
	public Optional<CajaChica> buscarId(@PathVariable("id") Integer id) {
		return serviceCajaChica.buscarId(id);
	}

	@DeleteMapping("/caja_chica/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceCajaChica.eliminar(id);
		return "Registro Eliminado";
	}
}
