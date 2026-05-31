package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Notificacion;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByTenant(Tenants tenant);
    List<Notificacion> findByUsuario(Usuarios usuario);
    List<Notificacion> findByEstadoLectura(Notificacion.EstadoNotificacion estado);
}
