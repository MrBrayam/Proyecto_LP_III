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

import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.service.ITenantsService;

public class TenantController {
    @Autowired
    private ITenantsService serviceTenants;

    @GetMapping("/tenants") //Metodo Get en Postman
    public List<Tenants> buscarTodos(){
        return serviceTenants.buscarTodos();
    }

    @PostMapping("/tenants")
    public Tenants guardar(@RequestBody Tenants registro) {
        serviceTenants.guardar(registro);
        return registro;
    }
    
    @PutMapping("/tenants")
    public Tenants modificar(@RequestBody Tenants registro) {
        serviceTenants.modificar(registro);
        return registro;
    }

    @GetMapping("/tenants/{id}")
    public Optional<Tenants> buscarId(@PathVariable("id")Integer id) {
        return serviceTenants.buscarId(id);
    }
    
    @DeleteMapping("/tenants/{id}")
    public String elminar(@PathVariable Integer id){
        serviceTenants.eliminar(id);
        return "Registro Eliminado";
    }
}
