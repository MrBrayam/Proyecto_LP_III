package proyecto.lp.iii.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.service.ISuscripcionService;
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
@RequestMapping("/api")
public class UsuariosController {
    @Autowired
    private IUsuariosService serviceUsuarios;

    @Autowired
    private ISuscripcionService serviceSuscripcion;

    @GetMapping("/usuarios") //Metodo Get en Postman
    public List<Usuarios> buscarTodos(){
        return serviceUsuarios.buscarTodos();
    }

    @PostMapping("/usuarios")
    public Usuarios guardar(@RequestBody Usuarios registro) {
        if (registro.getId_usuarios() == null && registro.getId_tenants() != null) {
            Integer idTenant = registro.getId_tenants().getId_tenants();
            Optional<Suscripcion> activeSubOpt = serviceSuscripcion.buscarTodos().stream()
                .filter(s -> s.getId_tenants() != null 
                    && s.getId_tenants().getId_tenants().equals(idTenant)
                    && s.getEstado() != null && s.getEstado() == 1)
                .findFirst();

            if (activeSubOpt.isPresent()) {
                String planName = activeSubOpt.get().getId_planes_suscripcion().getNombre_plan_suscripcion();
                int limit = -1;
                if ("Plan Básico".equalsIgnoreCase(planName)) {
                    limit = 5;
                } else if ("Plan Profesional".equalsIgnoreCase(planName)) {
                    limit = 15;
                }

                if (limit != -1) {
                    long count = serviceUsuarios.buscarTodos().stream()
                        .filter(u -> u.getId_tenants() != null 
                            && u.getId_tenants().getId_tenants().equals(idTenant)
                            && u.getEstado() != null && u.getEstado() == 1)
                        .count();
                    if (count >= limit) {
                        throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Su plan actual (" + planName + ") permite un máximo de " + limit + " usuarios. Actualice su plan para agregar más."
                        );
                    }
                }
            }
        }

        // Encrypt password before saving if raw
        String pwd = registro.getContrasenia();
        if (pwd != null && !pwd.trim().isEmpty()) {
            if (!pwd.startsWith("$2a$") && !pwd.startsWith("$2b$") && !pwd.startsWith("$2y$")) {
                String hashed = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(pwd, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
                registro.setContrasenia(hashed);
            }
        }

        serviceUsuarios.guardar(registro);
        return registro;
    }
    
    @PutMapping("/usuarios/{id}")
    public Usuarios modificar(@PathVariable Integer id, @RequestBody Usuarios registro) {
        registro.setId_usuarios(id);
        
        // Preserve password if left empty, otherwise encrypt the new password
        Optional<Usuarios> originalOpt = serviceUsuarios.buscarId(id);
        if (originalOpt.isPresent()) {
            Usuarios original = originalOpt.get();
            String pwd = registro.getContrasenia();
            if (pwd == null || pwd.trim().isEmpty()) {
                registro.setContrasenia(original.getContrasenia());
            } else if (!pwd.startsWith("$2a$") && !pwd.startsWith("$2b$") && !pwd.startsWith("$2y$")) {
                String hashed = org.springframework.security.crypto.bcrypt.BCrypt.hashpw(pwd, org.springframework.security.crypto.bcrypt.BCrypt.gensalt());
                registro.setContrasenia(hashed);
            }
        }

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

    @GetMapping("/usuarios/correo/{correo}")
    public Optional<Usuarios> buscarPorCorreo(@PathVariable String correo) {
        return serviceUsuarios.buscarPorCorreo(correo);
    }

    @GetMapping("/usuarios/tenant/{idTenants}")
    public List<Usuarios> buscarPorTenant(@PathVariable Integer idTenants) {
        return serviceUsuarios.buscarPorTenant(idTenants);
    }
}
