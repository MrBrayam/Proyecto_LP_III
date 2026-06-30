package proyecto.lp.iii.api.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.lp.iii.api.entity.Producto;
import proyecto.lp.iii.api.repository.ProductoRepository;
import proyecto.lp.iii.api.service.IProductoService;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository repoProducto;
    
    public List<Producto> buscarTodos() { 
        return repoProducto.findAll(); 
    }
    public void guardar(Producto producto) { 
        repoProducto.save(producto); 
    }
    @Transactional
    public void modificar(Producto producto) {
        Producto existente = repoProducto.findById(producto.getId_productos())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + producto.getId_productos()));
        if (producto.getId_categorias_productos() != null) {
            existente.setId_categorias_productos(producto.getId_categorias_productos());
        }
        if (producto.getId_marcas() != null) {
            existente.setId_marcas(producto.getId_marcas());
        }
        existente.setCodigo_interno(producto.getCodigo_interno());
        existente.setCodigo_barras(producto.getCodigo_barras());
        existente.setNombre_producto(producto.getNombre_producto());
        existente.setDescripcion(producto.getDescripcion());
        existente.setTipo_producto(producto.getTipo_producto());
        existente.setPresentacion(producto.getPresentacion());
        existente.setContenido_neto(producto.getContenido_neto());
        existente.setImg_url(producto.getImg_url());
        if (producto.getPrecio_costo() != null) {
            existente.setPrecio_costo(producto.getPrecio_costo());
        }
        if (producto.getPrecio_venta() != null) {
            existente.setPrecio_venta(producto.getPrecio_venta());
        }
        if (producto.getStock_minimo() != null) {
            existente.setStock_minimo(producto.getStock_minimo());
        }
        if (producto.getStock_critico() != null) {
            existente.setStock_critico(producto.getStock_critico());
        }
        if (producto.getVisible_storefront() != null) {
            existente.setVisible_storefront(producto.getVisible_storefront());
        }
        existente.setEtiqueta_especial(producto.getEtiqueta_especial());
        if (producto.getEstado() != null) {
            existente.setEstado(producto.getEstado());
        }
        repoProducto.save(existente);
    }
    public Optional<Producto> buscarId(Integer id) { 
        return repoProducto.findById(id); 
    }
    public void eliminar(Integer id) { 
        repoProducto.deleteById(id); 
    }
}
