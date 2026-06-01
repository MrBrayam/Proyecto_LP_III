package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PermisoRol;
import proyecto.lp.iii.api.entity.RolPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRolRepository extends JpaRepository<PermisoRol, Integer> {
    @Query("SELECT p FROM PermisoRol p WHERE p.id_roles_personalizados = :rol")
    List<PermisoRol> findByRol(@Param("rol") RolPersonalizado rol);
}
