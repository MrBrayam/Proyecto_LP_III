package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Auditoria;
import proyecto.lp.iii.api.repository.AuditoriaRepository;
import proyecto.lp.iii.api.service.IAuditoriaService;

@Service
public class AuditoriaService implements IAuditoriaService {
    @Autowired
    private AuditoriaRepository repoAuditoria;
    
    @Autowired
    private EntityManager entityManager;

    public List<Auditoria> buscarTodos() {
        return repoAuditoria.findAll();
    }

    public void guardar(Auditoria auditoria) {
        repoAuditoria.save(auditoria);
    }

    @Transactional
    public void modificar(Auditoria auditoria) {
        entityManager.merge(auditoria);
    }

    public Optional<Auditoria> buscarId(Integer id) {
        return repoAuditoria.findById(id);
    }

    public void eliminar(Integer id) {
        repoAuditoria.deleteById(id);
    }
}
