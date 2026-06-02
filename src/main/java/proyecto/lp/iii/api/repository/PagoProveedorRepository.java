package proyecto.lp.iii.api.repository;

import proyecto.lp.iii.api.entity.PagoProveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoProveedorRepository extends JpaRepository<PagoProveedor, Integer> {
}
