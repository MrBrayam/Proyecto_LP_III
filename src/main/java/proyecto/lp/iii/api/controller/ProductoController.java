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

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.service.IProductoService;

@RestController
@RequestMapping("/api")
public class ProductoController {
@Autowired
private IProductoService serviceProducto;

@GetMapping("/productos")
public List<Producto> buscarTodos() {
return serviceProducto.buscarTodos();
}

	@PostMapping("/productos")
	public Producto guardar(@RequestBody Producto registro) {
		if (registro.getCodigo_interno() == null || registro.getCodigo_interno().trim().isEmpty()) {
			int nextId = serviceProducto.buscarTodos().size() + 1;
			registro.setCodigo_interno(String.format("PROD-%05d", nextId));
		}
		if (registro.getCodigo_barras() == null || registro.getCodigo_barras().trim().isEmpty()) {
			int nextId = serviceProducto.buscarTodos().size() + 1;
			registro.setCodigo_barras(generateEAN13(nextId));
		}
		serviceProducto.guardar(registro);
		return registro;
	}

	@PutMapping("/productos/{id}")
	public Producto modificar(@PathVariable Integer id, @RequestBody Producto registro) {
		registro.setId_productos(id);
		if (registro.getCodigo_interno() == null || registro.getCodigo_interno().trim().isEmpty()) {
			int nextId = serviceProducto.buscarTodos().size() + 1;
			registro.setCodigo_interno(String.format("PROD-%05d", nextId));
		}
		if (registro.getCodigo_barras() == null || registro.getCodigo_barras().trim().isEmpty()) {
			int nextId = serviceProducto.buscarTodos().size() + 1;
			registro.setCodigo_barras(generateEAN13(nextId));
		}
		serviceProducto.modificar(registro);
		return registro;
	}

	private String generateEAN13(int nextId) {
		String base = "775" + String.format("%09d", nextId);
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			int digit = Character.getNumericValue(base.charAt(i));
			if (i % 2 == 0) {
				sum += digit;
			} else {
				sum += digit * 3;
			}
		}
		int checkDigit = (10 - (sum % 10)) % 10;
		return base + checkDigit;
	}

@GetMapping("/productos/{id}")
public Optional<Producto> buscarId(@PathVariable("id") Integer id) {
return serviceProducto.buscarId(id);
}

@DeleteMapping("/productos/{id}")
public String elminar(@PathVariable Integer id) {
serviceProducto.eliminar(id);
return "Registro Eliminado";
}
}
