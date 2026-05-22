package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Repartidor;
import proyecto.lp.iii.api.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    Repartidor findByUsuario(Usuario usuario);
    List<Repartidor> findByEstado(Repartidor.EstadoRepartidor estado);
}
