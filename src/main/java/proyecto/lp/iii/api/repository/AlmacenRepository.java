package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Almacen;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, Integer> {
    @Query("SELECT a FROM Almacen a WHERE a.id_sedes = :sede")
    List<Almacen> findBySede(@Param("sede") Sede sede);
}
