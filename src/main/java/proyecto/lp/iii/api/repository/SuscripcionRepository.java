package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
    List<Suscripcion> findByTenant(Tenant tenant);
    List<Suscripcion> findByEstado(Suscripcion.EstadoSuscripcion estado);
    List<Suscripcion> findByTenantAndEstado(Tenant tenant, Suscripcion.EstadoSuscripcion estado);
}
