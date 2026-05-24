package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Reclamo;
import proyecto.lp.iii.api.entity.Tenant;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Integer> {
    List<Reclamo> findByTenant(Tenant tenant);
    List<Reclamo> findByCliente(Cliente cliente);
    List<Reclamo> findByEstado(Reclamo.EstadoReclamo estado);
}
