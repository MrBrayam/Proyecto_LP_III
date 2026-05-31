package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Cliente;
import proyecto.lp.iii.api.repository.ClienteRepository;
import proyecto.lp.iii.api.service.IClienteService;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository repoCliente;

    public List<Cliente> buscarTodos() {
        return repoCliente.findAll();
    }

    public void guardar(Cliente cliente) {
        repoCliente.save(cliente);
    }

    public void modificar(Cliente cliente) {
        repoCliente.save(cliente);
    }

    public Optional<Cliente> buscarId(Integer id) {
        return repoCliente.findById(id);
    }

    public void eliminar(Integer id) {
        repoCliente.deleteById(id);
    }
}
