package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.FacturaSuscripcion;
import proyecto.lp.iii.api.entity.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaSuscripcionRepository extends JpaRepository<FacturaSuscripcion, Integer> {
    @Query("SELECT f FROM FacturaSuscripcion f WHERE f.id_suscripciones = :suscripcion")
    List<FacturaSuscripcion> findBySuscripcion(@Param("suscripcion") Suscripcion suscripcion);
}
