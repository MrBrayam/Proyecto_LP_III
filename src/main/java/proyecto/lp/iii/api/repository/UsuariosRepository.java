package proyecto.lp.iii.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.lp.iii.api.entity.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
    @Query("SELECT u FROM Usuarios u WHERE u.id_usuarios = :id")
    Optional<Usuarios> findById(@Param("id") Integer id);
}

