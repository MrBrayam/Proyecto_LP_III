package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.ZonaDelivery;
import proyecto.lp.iii.api.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ZonaDeliveryRepository extends JpaRepository<ZonaDelivery, Integer> {
    @Query("SELECT z FROM ZonaDelivery z WHERE z.id_sedes = :sede")
    List<ZonaDelivery> findBySede(@Param("sede") Sede sede);
}
