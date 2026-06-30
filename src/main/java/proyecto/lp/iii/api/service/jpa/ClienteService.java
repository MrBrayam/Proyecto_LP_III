package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Cliente;
import proyecto.lp.iii.api.repository.ClienteRepository;
import proyecto.lp.iii.api.service.IClienteService;

@Service
public class ClienteService implements IClienteService {
    @Autowired
    private ClienteRepository repoCliente;
    
    @Autowired
    private EntityManager entityManager;

    public List<Cliente> buscarTodos() {
        return repoCliente.findAll();
    }

    public void guardar(Cliente cliente) {
        repoCliente.save(cliente);
    }

    @Transactional
    public void modificar(Cliente cliente) {
        entityManager.merge(cliente);
    }

    public Optional<Cliente> buscarId(Integer id) {
        return repoCliente.findById(id);
    }

    public void eliminar(Integer id) {
        repoCliente.deleteById(id);
    }
}
