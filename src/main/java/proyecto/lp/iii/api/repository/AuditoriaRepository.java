package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditoriaRepository extends JpaRepository<Auditoria, Integer> {
    @Query("SELECT a FROM Auditoria a WHERE a.id_tenants.id_tenants = :idTenants")
    List<Auditoria> findByIdTenantsIdTenants(@Param("idTenants") Integer idTenants);
}
