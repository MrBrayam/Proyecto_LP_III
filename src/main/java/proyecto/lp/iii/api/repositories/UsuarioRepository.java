package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Usuario;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCorreo(String correo);
    List<Usuario> findByTenant(Tenant tenant);
    List<Usuario> findByTenantAndEstado(Tenant tenant, Usuario.EstadoUsuario estado);
    List<Usuario> findByTenantAndTipoUsuario(Tenant tenant, Usuario.TipoUsuario tipoUsuario);
}
