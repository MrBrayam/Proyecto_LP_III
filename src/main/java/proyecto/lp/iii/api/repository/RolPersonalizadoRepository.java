package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.RolPersonalizado;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RolPersonalizadoRepository extends JpaRepository<RolPersonalizado, Integer> {
    List<RolPersonalizado> findByTenant(Tenants tenant);

}
