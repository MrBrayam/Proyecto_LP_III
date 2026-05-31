package proyecto.lp.iii.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proyecto.lp.iii.api.entity.Usuarios;


public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {
}
