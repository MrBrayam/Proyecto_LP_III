package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionCajaRepository extends JpaRepository<SesionCaja, Integer> {
    List<SesionCaja> findBySede(Sede sede);
    List<SesionCaja> findByUsuario(Usuario usuario);
    List<SesionCaja> findByEstado(SesionCaja.EstadoSesion estado);
}
