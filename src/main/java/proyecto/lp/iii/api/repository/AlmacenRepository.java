package proyecto.lp.iii.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.lp.iii.api.entity.Almacen;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {

}
