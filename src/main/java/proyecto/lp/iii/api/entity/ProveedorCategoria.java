package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "proveedor_categorias")
@SQLDelete(sql = "DELETE FROM proveedor_categorias WHERE id_proveedor_categorias=?")
@SQLRestriction("1=1")
@JsonPropertyOrder({
        "id_proveedor_categorias",
        "id_proveedores",
        "id_categorias_productos"
})
public class ProveedorCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proveedor_categorias;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor id_proveedores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorias_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CategoriaProducto id_categorias_productos;

    public Integer getId_proveedor_categorias() {
        return id_proveedor_categorias;
    }

    public void setId_proveedor_categorias(Integer id_proveedor_categorias) {
        this.id_proveedor_categorias = id_proveedor_categorias;
    }

    public Proveedor getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(Proveedor id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public CategoriaProducto getId_categorias_productos() {
        return id_categorias_productos;
    }

    public void setId_categorias_productos(CategoriaProducto id_categorias_productos) {
        this.id_categorias_productos = id_categorias_productos;
    }

    @Override
    public String toString() {
        return "ProveedorCategoria [id_proveedor_categorias=" + id_proveedor_categorias + ", id_proveedores="
                + id_proveedores + ", id_categorias_productos=" + id_categorias_productos + "]";
    }

    
}
