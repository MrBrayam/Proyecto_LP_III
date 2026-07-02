package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.RolPersonalizado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolPersonalizadoRepository extends JpaRepository<RolPersonalizado, Integer> {
    @Query("SELECT r FROM RolPersonalizado r WHERE r.id_tenants.id_tenants = :idTenants")
    List<RolPersonalizado> findByIdTenantsIdTenants(@Param("idTenants") Integer idTenants);
}
