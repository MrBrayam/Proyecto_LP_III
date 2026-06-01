package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Cita;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {
    @Query("SELECT c FROM Cita c WHERE c.id_tenants = :tenant")
    List<Cita> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT c FROM Cita c WHERE c.id_sedes = :sede")
    List<Cita> findBySede(@Param("sede") Sede sede);

    @Query("SELECT c FROM Cita c WHERE c.id_clientes = :cliente")
    List<Cita> findByCliente(@Param("cliente") Cliente cliente);
}
