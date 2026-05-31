package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proyecto.lp.iii.api.entity.Marca;
import proyecto.lp.iii.api.repository.MarcaRepository;
import proyecto.lp.iii.api.service.IMarcaService;

@Service
public class MarcaService implements IMarcaService {
    @Autowired
    private MarcaRepository repoMarca;

    public List<Marca> buscarTodos() {
        return repoMarca.findAll();
    }

    public void guardar(Marca marca) {
        repoMarca.save(marca);
    }

    public void modificar(Marca marca) {
        repoMarca.save(marca);
    }

    public Optional<Marca> buscarId(Integer id) {
        return repoMarca.findById(id);
    }

    public void eliminar(Integer id) {
        repoMarca.deleteById(id);
    }
}
