package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.SesionCaja;
import proyecto.lp.iii.api.entities.Sede;
import proyecto.lp.iii.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionCajaRepository extends JpaRepository<SesionCaja, Integer> {
    List<SesionCaja> findBySede(Sede sede);
    List<SesionCaja> findByUsuario(Usuario usuario);
    List<SesionCaja> findByEstado(SesionCaja.EstadoSesion estado);
}
