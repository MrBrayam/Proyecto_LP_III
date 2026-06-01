package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Notificacion;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    @Query("SELECT n FROM Notificacion n WHERE n.id_tenants = :tenant")
    List<Notificacion> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT n FROM Notificacion n WHERE n.id_usuarios = :usuario")
    List<Notificacion> findByUsuario(@Param("usuario") Usuarios usuario);
}
