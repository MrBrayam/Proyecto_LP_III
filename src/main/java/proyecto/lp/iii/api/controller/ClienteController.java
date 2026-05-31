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

import proyecto.lp.iii.api.entity.Cliente;
import proyecto.lp.iii.api.service.IClienteService;

@RestController
@RequestMapping("/api")
public class ClienteController {
	@Autowired
	private IClienteService serviceCliente;

	@GetMapping("/clientes")
	public List<Cliente> buscarTodos() {
		return serviceCliente.buscarTodos();
	}

	@PostMapping("/clientes")
	public Cliente guardar(@RequestBody Cliente registro) {
		serviceCliente.guardar(registro);
		return registro;
	}

	@PutMapping("/clientes")
	public Cliente modificar(@RequestBody Cliente registro) {
		serviceCliente.modificar(registro);
		return registro;
	}

	@GetMapping("/clientes/{id}")
	public Optional<Cliente> buscarId(@PathVariable("id") Integer id) {
		return serviceCliente.buscarId(id);
	}

	@DeleteMapping("/clientes/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceCliente.eliminar(id);
		return "Registro Eliminado";
	}
}
