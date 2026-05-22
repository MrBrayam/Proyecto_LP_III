package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.PermisoRol;
import proyecto.lp.iii.api.entities.RolPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRolRepository extends JpaRepository<PermisoRol, Integer> {
    List<PermisoRol> findByRol(RolPersonalizado rol);
    List<PermisoRol> findByRolAndEstado(RolPersonalizado rol, PermisoRol.EstadoPermiso estado);
}
