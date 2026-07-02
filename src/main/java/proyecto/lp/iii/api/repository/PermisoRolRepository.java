package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PermisoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PermisoRolRepository extends JpaRepository<PermisoRol, Integer> {
    @Query("SELECT p FROM PermisoRol p WHERE p.id_roles_personalizados.id_roles_personalizados = :idRol")
    List<PermisoRol> findByIdRolesPersonalizadosIdRolesPersonalizados(@Param("idRol") Integer idRol);
}
