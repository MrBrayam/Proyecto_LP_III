package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    List<Marca> findByTenant(Tenant tenant);
    List<Marca> findByTenantAndEstado(Tenant tenant, Marca.EstadoMarca estado);
}
