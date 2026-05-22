package proyecto.lp.iii.api.repositories;

import proyecto.lp.iii.api.entities.Almacen;
import proyecto.lp.iii.api.entities.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    List<Almacen> findBySede(Sede sede);
    List<Almacen> findBySedeAndEstado(Sede sede, Almacen.EstadoAlmacen estado);
}
