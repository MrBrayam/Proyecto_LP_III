package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Cliente;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("SELECT c FROM Cliente c WHERE c.id_tenants = :tenant")
    List<Cliente> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT c FROM Cliente c WHERE c.numero_documento = :numeroDocumento")
    Cliente findByNumeroDocumento(@Param("numeroDocumento") String numeroDocumento);
}
