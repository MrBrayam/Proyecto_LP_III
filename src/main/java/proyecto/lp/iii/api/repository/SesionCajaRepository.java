package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.SesionCaja;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SesionCajaRepository extends JpaRepository<SesionCaja, Integer> {
    @Query("SELECT s FROM SesionCaja s WHERE s.id_sedes = :sede")
    List<SesionCaja> findBySede(@Param("sede") Sede sede);

    List<SesionCaja> findByUsuario(Usuarios usuario);

}
