package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ComboPromocional;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ComboPromocionalRepository extends JpaRepository<ComboPromocional, Integer> {
    @Query("SELECT c FROM ComboPromocional c WHERE c.id_tenants = :tenant")
    List<ComboPromocional> findByTenant(@Param("tenant") Tenants tenant);
}
