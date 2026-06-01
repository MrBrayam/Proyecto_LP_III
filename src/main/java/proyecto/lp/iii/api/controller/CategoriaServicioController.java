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

import proyecto.lp.iii.api.entity.CategoriaServicio;
import proyecto.lp.iii.api.service.ICategoriaServicioService;

@RestController
@RequestMapping("/api")
public class CategoriaServicioController {
    @Autowired
    private ICategoriaServicioService serviceCategoriaServicio;

    @GetMapping("/categorias_servicios")
    public List<CategoriaServicio> buscarTodos() {
        return serviceCategoriaServicio.buscarTodos();
    }

    @PostMapping("/categorias_servicios")
    public CategoriaServicio guardar(@RequestBody CategoriaServicio registro) {
        serviceCategoriaServicio.guardar(registro);
        return registro;
    }

    @PutMapping("/categorias_servicios")
    public CategoriaServicio modificar(@RequestBody CategoriaServicio registro) {
        serviceCategoriaServicio.modificar(registro);
        return registro;
    }

    @GetMapping("/categorias_servicios/{id}")
    public Optional<CategoriaServicio> buscarId(@PathVariable("id") Integer id) {
        return serviceCategoriaServicio.buscarId(id);
    }

    @DeleteMapping("/categorias_servicios/{id}")
    public String elminar(@PathVariable Integer id) {
        serviceCategoriaServicio.eliminar(id);
        return "Registro Eliminado";
    }
}
