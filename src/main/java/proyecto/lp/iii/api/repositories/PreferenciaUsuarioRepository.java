package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.PreferenciaUsuario;
import proyecto.lp.iii.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaUsuarioRepository extends JpaRepository<PreferenciaUsuario, Integer> {
    PreferenciaUsuario findByUsuario(Usuario usuario);
}
