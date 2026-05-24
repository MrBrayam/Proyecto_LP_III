package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Cita;
import proyecto.lp.iii.api.entity.Tenant;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByTenant(Tenant tenant);
    List<Cita> findBySede(Sede sede);
    List<Cita> findByCliente(Cliente cliente);
    List<Cita> findByEstado(Cita.EstadoCita estado);
}
