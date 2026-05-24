package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboPromocionalRepository extends JpaRepository<ComboPromocional, Integer> {
    List<ComboPromocional> findByTenant(Tenant tenant);
    List<ComboPromocional> findByTenantAndEstado(Tenant tenant, ComboPromocional.EstadoCombo estado);
}
