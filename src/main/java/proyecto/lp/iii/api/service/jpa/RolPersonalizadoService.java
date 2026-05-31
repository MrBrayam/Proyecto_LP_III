package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.RolPersonalizado;
import proyecto.lp.iii.api.repository.RolPersonalizadoRepository;
import proyecto.lp.iii.api.service.IRolPersonalizadoService;

@Service
public class RolPersonalizadoService implements IRolPersonalizadoService {
    @Autowired
    private RolPersonalizadoRepository repoRolPersonalizado;

    public List<RolPersonalizado> buscarTodos() {
        return repoRolPersonalizado.findAll();
    }

    public void guardar(RolPersonalizado rolPersonalizado) {
        repoRolPersonalizado.save(rolPersonalizado);
    }

    public void modificar(RolPersonalizado rolPersonalizado) {
        repoRolPersonalizado.save(rolPersonalizado);
    }

    public Optional<RolPersonalizado> buscarId(Integer id) {
        return repoRolPersonalizado.findById(id);
    }

    public void eliminar(Integer id) {
        repoRolPersonalizado.deleteById(id);
    }
}
