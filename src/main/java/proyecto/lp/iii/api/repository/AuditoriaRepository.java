package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Auditoria;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    List<Auditoria> findByTenant(Tenants tenant);
    List<Auditoria> findByTenantAndUsuario(Tenants tenant, Usuarios usuario);
    List<Auditoria> findByAccion(String accion);
}

