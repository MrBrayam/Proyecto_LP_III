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

import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.service.IPedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {
@Autowired
private IPedidoService servicePedido;

@GetMapping("/pedidos")
public List<Pedido> buscarTodos() {
return servicePedido.buscarTodos();
}

@PostMapping("/pedidos")
public Pedido guardar(@RequestBody Pedido registro) {
servicePedido.guardar(registro);
return registro;
}

@PutMapping("/pedidos")
public Pedido modificar(@RequestBody Pedido registro) {
servicePedido.modificar(registro);
return registro;
}

@GetMapping("/pedidos/{id}")
public Optional<Pedido> buscarId(@PathVariable("id") Integer id) {
return servicePedido.buscarId(id);
}

@DeleteMapping("/pedidos/{id}")
public String elminar(@PathVariable Integer id) {
servicePedido.eliminar(id);
return "Registro Eliminado";
}
}
