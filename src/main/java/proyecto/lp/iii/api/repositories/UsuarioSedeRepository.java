package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.UsuarioSede;
import proyecto.lp.iii.api.entities.Usuario;
import proyecto.lp.iii.api.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioSedeRepository extends JpaRepository<UsuarioSede, Integer> {
    List<UsuarioSede> findByUsuario(Usuario usuario);
    List<UsuarioSede> findBySede(Sede sede);
}
