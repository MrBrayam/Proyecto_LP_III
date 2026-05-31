package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "categorias_productos")
@SQLDelete(sql = "UPDATE categorias_productos SET estado=0 WHERE id_categorias_productos=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_categorias_productos",
        "id_tenants",
        "nombre_categoria_producto",
        "descripcion",
        "imagen_url",
        "orden",
        "estado"
})
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categorias_productos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "nombre_categoria_producto", nullable = false, length = 100)
    private String nombre_categoria_producto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "imagen_url", length = 255)
    private String imagen_url;

    @Column
    private Integer orden;

    @Column
    private Integer estado = 1;

    public Integer getId_categorias_productos() {
        return id_categorias_productos;
    }

    public void setId_categorias_productos(Integer id_categorias_productos) {
        this.id_categorias_productos = id_categorias_productos;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_categoria_producto() {
        return nombre_categoria_producto;
    }

    public void setNombre_categoria_producto(String nombre_categoria_producto) {
        this.nombre_categoria_producto = nombre_categoria_producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CategoriaProducto [id_categorias_productos=" + id_categorias_productos + ", id_tenants="
                + id_tenants + ", nombre_categoria_producto=" + nombre_categoria_producto + ", descripcion="
                + descripcion + ", imagen_url=" + imagen_url + ", orden=" + orden + ", estado=" + estado + "]";
    }
}
