package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Repartidor;
import proyecto.lp.iii.api.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepartidorRepository extends JpaRepository<Repartidor, Integer> {
    @Query("SELECT r FROM Repartidor r WHERE r.id_usuarios = :usuario")
    Repartidor findByUsuario(@Param("usuario") Usuarios usuario);

}
