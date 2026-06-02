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

import proyecto.lp.iii.api.entity.SerieComprobante;
import proyecto.lp.iii.api.service.ISerieComprobanteService;

@RestController
@RequestMapping("/api")
public class SerieComprobanteController {
@Autowired
private ISerieComprobanteService serviceSerieComprobante;

@GetMapping("/series_comprobantes")
public List<SerieComprobante> buscarTodos() {
return serviceSerieComprobante.buscarTodos();
}

@PostMapping("/series_comprobantes")
public SerieComprobante guardar(@RequestBody SerieComprobante registro) {
serviceSerieComprobante.guardar(registro);
return registro;
}

@PutMapping("/series_comprobantes")
public SerieComprobante modificar(@RequestBody SerieComprobante registro) {
serviceSerieComprobante.modificar(registro);
return registro;
}

@GetMapping("/series_comprobantes/{id}")
public Optional<SerieComprobante> buscarId(@PathVariable("id") Integer id) {
return serviceSerieComprobante.buscarId(id);
}

@DeleteMapping("/series_comprobantes/{id}")
public String elminar(@PathVariable Integer id) {
serviceSerieComprobante.eliminar(id);
return "Registro Eliminado";
}
}
