package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    List<Almacen> findBySede(Sede sede);
    List<Almacen> findBySedeAndEstado(Sede sede, Almacen.EstadoAlmacen estado);
}
