package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Marca;
import proyecto.lp.iii.api.entities.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    List<Marca> findByTenant(Tenant tenant);
    List<Marca> findByTenantAndEstado(Tenant tenant, Marca.EstadoMarca estado);
}
