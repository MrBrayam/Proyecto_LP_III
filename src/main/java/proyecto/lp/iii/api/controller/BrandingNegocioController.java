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

import proyecto.lp.iii.api.entity.BrandingNegocio;
import proyecto.lp.iii.api.service.IBrandingNegocioService;

@RestController
@RequestMapping("/api")
public class BrandingNegocioController {
	@Autowired
	private IBrandingNegocioService serviceBrandingNegocio;

	@GetMapping("/branding_negocio")
	public List<BrandingNegocio> buscarTodos() {
		return serviceBrandingNegocio.buscarTodos();
	}

	@PostMapping("/branding_negocio")
	public BrandingNegocio guardar(@RequestBody BrandingNegocio registro) {
		serviceBrandingNegocio.guardar(registro);
		return registro;
	}

	@PutMapping("/branding_negocio")
	public BrandingNegocio modificar(@RequestBody BrandingNegocio registro) {
		serviceBrandingNegocio.modificar(registro);
		return registro;
	}

	@GetMapping("/branding_negocio/{id}")
	public Optional<BrandingNegocio> buscarId(@PathVariable("id") Integer id) {
		return serviceBrandingNegocio.buscarId(id);
	}

	@DeleteMapping("/branding_negocio/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceBrandingNegocio.eliminar(id);
		return "Registro Eliminado";
	}
}
