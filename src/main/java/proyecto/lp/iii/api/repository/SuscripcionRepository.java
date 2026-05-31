package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Suscripcion;
import proyecto.lp.iii.api.entity.Tenants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Integer> {
    List<Suscripcion> findByTenant(Tenants tenant);
    List<Suscripcion> findByEstado(Suscripcion.EstadoSuscripcion estado);
    List<Suscripcion> findByTenantAndEstado(Tenants tenant, Suscripcion.EstadoSuscripcion estado);
}
