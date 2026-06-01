package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.UsuarioSede;
import proyecto.lp.iii.api.entity.Usuarios;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioSedeRepository extends JpaRepository<UsuarioSede, Integer> {
    List<UsuarioSede> findByUsuario(Usuarios usuario);

    @Query("SELECT u FROM UsuarioSede u WHERE u.id_sedes = :sede")
    List<UsuarioSede> findBySede(@Param("sede") Sede sede);
}
