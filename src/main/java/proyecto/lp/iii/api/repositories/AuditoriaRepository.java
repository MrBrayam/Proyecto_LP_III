package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Auditoria;
import proyecto.lp.iii.api.entities.Tenant;
import proyecto.lp.iii.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    List<Auditoria> findByTenant(Tenant tenant);
    List<Auditoria> findByTenantAndUsuario(Tenant tenant, Usuario usuario);
    List<Auditoria> findByAccion(String accion);
}

