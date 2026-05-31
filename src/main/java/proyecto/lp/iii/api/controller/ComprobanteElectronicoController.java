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

import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import proyecto.lp.iii.api.service.IComprobanteElectronicoService;

@RestController
@RequestMapping("/api")
public class ComprobanteElectronicoController {
	@Autowired
	private IComprobanteElectronicoService serviceComprobanteElectronico;

	@GetMapping("/comprobantes_electronicos")
	public List<ComprobanteElectronico> buscarTodos() {
		return serviceComprobanteElectronico.buscarTodos();
	}

	@PostMapping("/comprobantes_electronicos")
	public ComprobanteElectronico guardar(@RequestBody ComprobanteElectronico registro) {
		serviceComprobanteElectronico.guardar(registro);
		return registro;
	}

	@PutMapping("/comprobantes_electronicos")
	public ComprobanteElectronico modificar(@RequestBody ComprobanteElectronico registro) {
		serviceComprobanteElectronico.modificar(registro);
		return registro;
	}

	@GetMapping("/comprobantes_electronicos/{id}")
	public Optional<ComprobanteElectronico> buscarId(@PathVariable("id") Integer id) {
		return serviceComprobanteElectronico.buscarId(id);
	}

	@DeleteMapping("/comprobantes_electronicos/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceComprobanteElectronico.eliminar(id);
		return "Registro Eliminado";
	}
}
