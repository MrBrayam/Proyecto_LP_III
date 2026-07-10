package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.Notificacion;
import proyecto.lp.iii.api.repository.NotificacionRepository;
import proyecto.lp.iii.api.service.INotificacionService;

@Service
public class NotificacionService implements INotificacionService {
    @Autowired
    private NotificacionRepository repoNotificacion;
    
    @Autowired
    private EntityManager entityManager;

    public List<Notificacion> buscarTodos() { 
        return repoNotificacion.findAll(); 
    }
    public void guardar(Notificacion notificacion) { 
        repoNotificacion.save(notificacion); 
    }
    @Transactional
    public void modificar(Notificacion notificacion) { 
        entityManager.merge(notificacion); 
    }
    public Optional<Notificacion> buscarId(Integer id) { 
        return repoNotificacion.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoNotificacion.deleteById(id); 
    }
}
