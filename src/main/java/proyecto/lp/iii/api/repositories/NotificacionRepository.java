package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Notificacion;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByTenant(Tenant tenant);
    List<Notificacion> findByUsuario(Usuario usuario);
    List<Notificacion> findByEstadoLectura(Notificacion.EstadoNotificacion estado);
}
