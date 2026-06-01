package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Auditoria;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    @Query("SELECT a FROM Auditoria a WHERE a.id_tenants = :tenant")
    List<Auditoria> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT a FROM Auditoria a WHERE a.id_tenants = :tenant AND a.id_usuarios = :usuario")
    List<Auditoria> findByTenantAndUsuario(@Param("tenant") Tenants tenant, @Param("usuario") Usuarios usuario);

    List<Auditoria> findByAccion(String accion);
}

