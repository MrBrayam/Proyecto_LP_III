package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.RolPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolPersonalizadoRepository extends JpaRepository<RolPersonalizado, Integer> {

}
