package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.ComprobanteElectronico;
import proyecto.lp.iii.api.repository.ComprobanteElectronicoRepository;
import proyecto.lp.iii.api.service.IComprobanteElectronicoService;

@Service
public class ComprobanteElectronicoService implements IComprobanteElectronicoService {
    @Autowired
    private ComprobanteElectronicoRepository repoComprobanteElectronico;
    
    @Autowired
    private EntityManager entityManager;

    public List<ComprobanteElectronico> buscarTodos() {
        return repoComprobanteElectronico.findAll();
    }

    public void guardar(ComprobanteElectronico comprobanteelectronico) {
        repoComprobanteElectronico.save(comprobanteelectronico);
    }

    @Transactional
    public void modificar(ComprobanteElectronico comprobanteelectronico) {
        entityManager.merge(comprobanteelectronico);
    }

    public Optional<ComprobanteElectronico> buscarId(Integer id) {
        return repoComprobanteElectronico.findById(id);
    }

    public void eliminar(Integer id) {
        repoComprobanteElectronico.deleteById(id);
    }
}
