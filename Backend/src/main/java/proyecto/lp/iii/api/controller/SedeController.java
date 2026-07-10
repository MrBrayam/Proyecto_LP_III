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

import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.service.ISedeService;

@RestController
@RequestMapping("/api")
public class SedeController {
    @Autowired
    private ISedeService serviceSede;

    @Autowired
    private ISuscripcionService serviceSuscripcion;

    @GetMapping("/sedes")
    public List<Sede> buscarTodos() {
        return serviceSede.buscarTodos();
    }

    @PostMapping("/sedes")
    public Sede guardar(@RequestBody Sede registro) {
        if (registro.getId_sedes() == null && registro.getId_tenants() != null) {
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
                    limit = 1;
                } else if ("Plan Profesional".equalsIgnoreCase(planName)) {
                    limit = 3;
                }

                if (limit != -1) {
                    long count = serviceSede.buscarTodos().stream()
                        .filter(s -> s.getId_tenants() != null 
                            && s.getId_tenants().getId_tenants().equals(idTenant)
                            && s.getEstado() != null && s.getEstado() == 1)
                        .count();
                    if (count >= limit) {
                        throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST,
                            "Su plan actual (" + planName + ") permite un máximo de " + limit + " sede(s). Actualice su plan para agregar más."
                        );
                    }
                }
            }
        }
        serviceSede.guardar(registro);
        return registro;
    }

@PutMapping("/sedes/{id}")
public Sede modificar(@PathVariable Integer id, @RequestBody Sede registro) {
	registro.setId_sedes(id);
	serviceSede.modificar(registro);
	return registro;
}

@GetMapping("/sedes/{id}")
public Optional<Sede> buscarId(@PathVariable("id") Integer id) {
return serviceSede.buscarId(id);
}

@DeleteMapping("/sedes/{id}")
public String elminar(@PathVariable Integer id) {
serviceSede.eliminar(id);
return "Registro Eliminado";
}
}
