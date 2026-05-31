package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.CuentaPorPagar;

public interface ICuentaPorPagarService {
    List<CuentaPorPagar> buscarTodos();

    void guardar(CuentaPorPagar cuentaPorPagar);

    void modificar(CuentaPorPagar cuentaPorPagar);

    Optional<CuentaPorPagar> buscarId(Integer id);

    void eliminar(Integer id);
}
