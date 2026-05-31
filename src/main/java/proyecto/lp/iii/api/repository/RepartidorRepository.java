package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Repartidor;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    Repartidor findByUsuario(Usuarios usuario);

}
