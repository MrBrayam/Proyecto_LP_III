package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Tenants;

public interface ITenantsService {
    List<Tenants> buscarTodos();

    void guardar(Tenants tenant);

    void modificar(Tenants tenant);

    Optional<Tenants> buscarId(Integer id);

    void eliminar(Integer id);
}
