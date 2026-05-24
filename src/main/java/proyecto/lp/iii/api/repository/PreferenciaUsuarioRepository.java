package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PreferenciaUsuario;
import proyecto.lp.iii.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaUsuarioRepository extends JpaRepository<PreferenciaUsuario, Integer> {
    PreferenciaUsuario findByUsuario(Usuario usuario);
}
