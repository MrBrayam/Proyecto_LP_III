package proyecto.lp.iii.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.lp.iii.api.entity.PreciosPlan;
import proyecto.lp.iii.api.entity.PlanSuscripcion;

@Repository
public interface PreciosPlanRepository extends JpaRepository<PreciosPlan, Integer> {
    @Query("SELECT p FROM PreciosPlan p WHERE p.id_planes_suscripcion = :plan AND p.estado = 1 ORDER BY p.id_precios_plan DESC")
    List<PreciosPlan> findByPlanesAndVigenteTrueOrderByFechaFinDesc(@Param("plan") PlanSuscripcion plan);

    @Query("SELECT p FROM PreciosPlan p WHERE p.id_precios_plan = :id")
    Optional<PreciosPlan> findById(@Param("id") Integer id);
}
