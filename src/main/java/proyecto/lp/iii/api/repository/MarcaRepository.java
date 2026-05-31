package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer> {
    List<Marca> findByTenant(Tenants tenant);
    List<Marca> findByTenantAndEstado(Tenants tenant, Marca.EstadoMarca estado);
}
