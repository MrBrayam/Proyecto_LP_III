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

import proyecto.lp.iii.api.entity.CategoriaProducto;
import proyecto.lp.iii.api.service.ICategoriaProductoService;

@RestController
@RequestMapping("/api")
public class CategoriaProductoController {
	@Autowired
	private ICategoriaProductoService serviceCategoriaProducto;

	@GetMapping("/categorias_productos")
	public List<CategoriaProducto> buscarTodos() {
		return serviceCategoriaProducto.buscarTodos();
	}

	@PostMapping("/categorias_productos")
	public CategoriaProducto guardar(@RequestBody CategoriaProducto registro) {
		serviceCategoriaProducto.guardar(registro);
		return registro;
	}

	@PutMapping("/categorias_productos")
	public CategoriaProducto modificar(@RequestBody CategoriaProducto registro) {
		serviceCategoriaProducto.modificar(registro);
		return registro;
	}

	@GetMapping("/categorias_productos/{id}")
	public Optional<CategoriaProducto> buscarId(@PathVariable("id") Integer id) {
		return serviceCategoriaProducto.buscarId(id);
	}

	@DeleteMapping("/categorias_productos/{id}")
	public String elminar(@PathVariable Integer id) {
		serviceCategoriaProducto.eliminar(id);
		return "Registro Eliminado";
	}
}
