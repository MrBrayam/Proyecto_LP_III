package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.entity.RolPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRolRepository extends JpaRepository<PermisoRol, Integer> {
    List<PermisoRol> findByRol(RolPersonalizado rol);
}
