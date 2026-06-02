package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PermisoRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRolRepository extends JpaRepository<PermisoRol, Integer> {

}
