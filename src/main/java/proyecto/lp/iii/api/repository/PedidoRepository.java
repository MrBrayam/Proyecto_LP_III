package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.Pedido;
import proyecto.lp.iii.api.entity.Tenants;
import proyecto.lp.iii.api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido p WHERE p.id_tenants = :tenant")
    List<Pedido> findByTenant(@Param("tenant") Tenants tenant);

    @Query("SELECT p FROM Pedido p WHERE p.id_clientes = :cliente")
    List<Pedido> findByCliente(@Param("cliente") Cliente cliente);
}
