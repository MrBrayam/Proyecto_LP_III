package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PreferenciaUsuario;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PreferenciaUsuarioRepository extends JpaRepository<PreferenciaUsuario, Integer> {
    @Query("SELECT p FROM PreferenciaUsuario p WHERE p.id_usuarios = :usuario")
    PreferenciaUsuario findByUsuario(@Param("usuario") Usuarios usuario);
}
