package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ServicioCita;
import proyecto.lp.iii.api.repository.ServicioCitaRepository;
import proyecto.lp.iii.api.service.IServicioCitaService;

@Service
public class ServicioCitaService implements IServicioCitaService {
    @Autowired
    private ServicioCitaRepository repo;

    public List<ServicioCita> buscarTodos() { return repo.findAll(); }
    public void guardar(ServicioCita entity) { repo.save(entity); }
    public void modificar(ServicioCita entity) { repo.save(entity); }
    public Optional<ServicioCita> buscarId(Integer id) { return repo.findById(id); }
    public void eliminar(Integer id) { repo.deleteById(id); }
}
