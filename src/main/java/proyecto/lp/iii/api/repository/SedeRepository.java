package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SedeRepository extends JpaRepository<Sede, Integer> {
    List<Sede> findByTenant(Tenants tenant);

}
