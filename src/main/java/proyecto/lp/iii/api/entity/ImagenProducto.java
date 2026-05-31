package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_producto")
@JsonPropertyOrder({
        "id_imagenes_producto",
        "id_productos",
        "url_imagen",
        "texto_alternativo",
        "es_principal",
        "orden"
})
public class ImagenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_imagenes_producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    private String url_imagen;
    private String texto_alternativo;
    private Integer es_principal = 0;
    private Integer orden;

    public Integer getId_imagenes_producto() {
        return id_imagenes_producto;
    }

    public void setId_imagenes_producto(Integer id_imagenes_producto) {
        this.id_imagenes_producto = id_imagenes_producto;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getTexto_alternativo() {
        return texto_alternativo;
    }

    public void setTexto_alternativo(String texto_alternativo) {
        this.texto_alternativo = texto_alternativo;
    }

    public Integer getEs_principal() {
        return es_principal;
    }

    public void setEs_principal(Integer es_principal) {
        this.es_principal = es_principal;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public String toString() {
        return "ImagenProducto [id_imagenes_producto=" + id_imagenes_producto + ", id_productos=" + id_productos
                + ", url_imagen=" + url_imagen + ", texto_alternativo=" + texto_alternativo + ", es_principal="
                + es_principal + ", orden=" + orden + "]";
    }
}
