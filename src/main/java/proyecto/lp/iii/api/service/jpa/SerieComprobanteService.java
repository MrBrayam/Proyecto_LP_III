package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.SerieComprobante;
import proyecto.lp.iii.api.repository.SerieComprobanteRepository;
import proyecto.lp.iii.api.service.ISerieComprobanteService;

@Service
public class SerieComprobanteService implements ISerieComprobanteService {
    @Autowired
    private SerieComprobanteRepository repoSerieComprobante;

    public List<SerieComprobante> buscarTodos() {
        return repoSerieComprobante.findAll();
    }

    public void guardar(SerieComprobante serieComprobante) {
        repoSerieComprobante.save(serieComprobante);
    }

    public void modificar(SerieComprobante serieComprobante) {
        repoSerieComprobante.save(serieComprobante);
    }

    public Optional<SerieComprobante> buscarId(Integer id) {
        return repoSerieComprobante.findById(id);
    }

    public void eliminar(Integer id) {
        repoSerieComprobante.deleteById(id);
    }
}
