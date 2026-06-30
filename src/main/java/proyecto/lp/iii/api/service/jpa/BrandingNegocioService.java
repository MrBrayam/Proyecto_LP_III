package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import proyecto.lp.iii.api.entity.BrandingNegocio;
import proyecto.lp.iii.api.repository.BrandingNegocioRepository;
import proyecto.lp.iii.api.service.IBrandingNegocioService;

@Service
public class BrandingNegocioService implements IBrandingNegocioService {
    @Autowired
    private BrandingNegocioRepository repoBrandingNegocio;
    
    @Autowired
    private EntityManager entityManager;

    public List<BrandingNegocio> buscarTodos() {
        return repoBrandingNegocio.findAll();
    }

    public void guardar(BrandingNegocio brandingnegocio) {
        repoBrandingNegocio.save(brandingnegocio);
    }

    @Transactional
    public void modificar(BrandingNegocio brandingnegocio) {
        entityManager.merge(brandingnegocio);
    }

    public Optional<BrandingNegocio> buscarId(Integer id) {
        return repoBrandingNegocio.findById(id);
    }

    public void eliminar(Integer id) {
        repoBrandingNegocio.deleteById(id);
    }
}
