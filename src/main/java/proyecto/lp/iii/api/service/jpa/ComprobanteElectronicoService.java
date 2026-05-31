package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import proyecto.lp.iii.api.repository.ComprobanteElectronicoRepository;
import proyecto.lp.iii.api.service.IComprobanteElectronicoService;

@Service
public class ComprobanteElectronicoService implements IComprobanteElectronicoService {
    @Autowired
    private ComprobanteElectronicoRepository repoComprobanteElectronico;

    public List<ComprobanteElectronico> buscarTodos() {
        return repoComprobanteElectronico.findAll();
    }

    public void guardar(ComprobanteElectronico comprobanteElectronico) {
        repoComprobanteElectronico.save(comprobanteElectronico);
    }

    public void modificar(ComprobanteElectronico comprobanteElectronico) {
        repoComprobanteElectronico.save(comprobanteElectronico);
    }

    public Optional<ComprobanteElectronico> buscarId(Integer id) {
        return repoComprobanteElectronico.findById(id);
    }

    public void eliminar(Integer id) {
        repoComprobanteElectronico.deleteById(id);
    }
}
