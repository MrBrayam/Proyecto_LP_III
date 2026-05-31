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

import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.service.IUsuariosService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {
        @Autowired
    private IUsuariosService serviceUsuarios;

    @GetMapping("/usuarios") //Metodo Get en Postman
    public List<Usuarios> buscarTodos(){
        return serviceUsuarios.buscarTodos();
    }

    @PostMapping("/usuarios")
    public Usuarios guardar(@RequestBody Usuarios registro) {
        serviceUsuarios.guardar(registro);
        return registro;
    }
    
    @PutMapping("/usuarios")
    public Usuarios modificar(@RequestBody Usuarios registro) {
        serviceUsuarios.modificar(registro);
        return registro;
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuarios> buscarId(@PathVariable("id")Integer id) {
        return serviceUsuarios.buscarId(id);
    }
    
    @DeleteMapping("/usuarios/{id}")
    public String elminar(@PathVariable Integer id){
        serviceUsuarios.eliminar(id);
        return "Registro Eliminado";
    }
}
