package proyecto.lp.iii.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.lp.iii.api.entity.Registros;

@Repository
public interface RegistrosRepository extends JpaRepository<Registros, Integer> {
    @Query("SELECT r FROM Registros r WHERE r.idregistro = :id")
    Optional<Registros> findById(@Param("id") Integer id);
}
