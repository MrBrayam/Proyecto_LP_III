package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboPromocionalRepository extends JpaRepository<ComboPromocional, Integer> {
    List<ComboPromocional> findByTenant(Tenants tenant);
    List<ComboPromocional> findByTenantAndEstado(Tenants tenant, ComboPromocional.EstadoCombo estado);
}
