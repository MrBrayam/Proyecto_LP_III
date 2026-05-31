package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.ComprobanteElectronico;

public interface IComprobanteElectronicoService {
    List<ComprobanteElectronico> buscarTodos();

    void guardar(ComprobanteElectronico comprobanteElectronico);

    void modificar(ComprobanteElectronico comprobanteElectronico);

    Optional<ComprobanteElectronico> buscarId(Integer id);

    void eliminar(Integer id);
}
