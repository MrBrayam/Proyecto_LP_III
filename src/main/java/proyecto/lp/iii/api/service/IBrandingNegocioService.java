package proyecto.lp.iii.api.service;

import java.util.List;
import java.util.Optional;

import proyecto.lp.iii.api.entity.BrandingNegocio;

public interface IBrandingNegocioService {
    List<BrandingNegocio> buscarTodos();

    void guardar(BrandingNegocio brandingNegocio);

    void modificar(BrandingNegocio brandingNegocio);

    Optional<BrandingNegocio> buscarId(Integer id);

    void eliminar(Integer id);
}
