package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ServicioBelleza;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioBellezaRepository extends JpaRepository<ServicioBelleza, Integer> {
    List<ServicioBelleza> findByTenant(Tenant tenant);
    List<ServicioBelleza> findByTenantAndEstado(Tenant tenant, ServicioBelleza.EstadoServicio estado);
}
