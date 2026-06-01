package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Venta;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Sede;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    @Query("SELECT v FROM Venta v WHERE v.id_tenants = :tenant")
    List<Venta> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT v FROM Venta v WHERE v.id_sedes = :sede")
    List<Venta> findBySede(@Param("sede") Sede sede);

    @Query("SELECT v FROM Venta v WHERE v.id_clientes = :cliente")
    List<Venta> findByCliente(@Param("cliente") Cliente cliente);
}
