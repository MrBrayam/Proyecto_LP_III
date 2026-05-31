package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.Cliente;

public interface IClienteService {
    List<Cliente> buscarTodos();

    void guardar(Cliente cliente);

    void modificar(Cliente cliente);

    Optional<Cliente> buscarId(Integer id);

    void eliminar(Integer id);
}
