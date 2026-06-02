package proyecto.lp.iii.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proyecto.lp.iii.api.entity.ProveedorCategoria;

@Repository
public interface ProveedorCategoriaRepository extends JpaRepository<ProveedorCategoria, Integer> {

}
