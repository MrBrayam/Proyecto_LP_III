package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.LoteInventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteInventarioRepository extends JpaRepository<LoteInventario, Integer> {

}
