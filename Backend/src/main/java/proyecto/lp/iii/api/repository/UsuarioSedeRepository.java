package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.UsuarioSede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioSedeRepository extends JpaRepository<UsuarioSede, Integer> {

}
