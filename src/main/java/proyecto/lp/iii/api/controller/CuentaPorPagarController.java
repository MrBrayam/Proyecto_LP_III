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

import proyecto.lp.iii.api.entity.CuentaPorPagar;
import proyecto.lp.iii.api.service.ICuentaPorPagarService;

@RestController
@RequestMapping("/api")
public class CuentaPorPagarController {
	@Autowired
	private ICuentaPorPagarService serviceCuentaPorPagar;

	@GetMapping("/cuentas_por_pagar")
	public List<CuentaPorPagar> buscarTodos() {
		return serviceCuentaPorPagar.buscarTodos();
	}

	@PostMapping("/cuentas_por_pagar")
	public CuentaPorPagar guardar(@RequestBody CuentaPorPagar registro) {
		serviceCuentaPorPagar.guardar(registro);
		return registro;
	}

	@PutMapping("/cuentas_por_pagar")
	public CuentaPorPagar modificar(@RequestBody CuentaPorPagar registro) {
		serviceCuentaPorPagar.modificar(registro);
		return registro;
	}

	@GetMapping("/cuentas_por_pagar/{id}")
	public Optional<CuentaPorPagar> buscarId(@PathVariable("id") Integer id) {
		return serviceCuentaPorPagar.buscarId(id);
	}

	@DeleteMapping("/cuentas_por_pagar/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceCuentaPorPagar.eliminar(id);
		return "Registro Eliminado";
	}
}
