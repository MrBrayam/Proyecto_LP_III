package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.ComboPromocional;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboPromocionalRepository extends JpaRepository<ComboPromocional, Integer> {
    List<ComboPromocional> findByTenant(Tenant tenant);
    List<ComboPromocional> findByTenantAndEstado(Tenant tenant, ComboPromocional.EstadoCombo estado);
}
