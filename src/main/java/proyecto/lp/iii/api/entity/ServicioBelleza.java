package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "servicios_belleza")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE servicios_belleza SET estado=0 WHERE id_servicios_belleza=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_servicios_belleza",
        "id_tenants",
        "nombre_servicio_belleza",
        "descripcion",
        "categoria",
        "duracion_minima",
        "duracion_maxima",
        "precio_base",
        "precio_editable",
        "estado"
})
public class ServicioBelleza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servicios_belleza;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String nombre_servicio_belleza;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String categoria;
    private Integer duracion_minima;
    private Integer duracion_maxima;
    private BigDecimal precio_base;
    private Integer precio_editable = 0;
    private Integer estado = 1;

    public Integer getId_servicios_belleza() {
        return id_servicios_belleza;
    }

    public void setId_servicios_belleza(Integer id_servicios_belleza) {
        this.id_servicios_belleza = id_servicios_belleza;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_servicio_belleza() {
        return nombre_servicio_belleza;
    }

    public void setNombre_servicio_belleza(String nombre_servicio_belleza) {
        this.nombre_servicio_belleza = nombre_servicio_belleza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getDuracion_minima() {
        return duracion_minima;
    }

    public void setDuracion_minima(Integer duracion_minima) {
        this.duracion_minima = duracion_minima;
    }

    public Integer getDuracion_maxima() {
        return duracion_maxima;
    }

    public void setDuracion_maxima(Integer duracion_maxima) {
        this.duracion_maxima = duracion_maxima;
    }

    public BigDecimal getPrecio_base() {
        return precio_base;
    }

    public void setPrecio_base(BigDecimal precio_base) {
        this.precio_base = precio_base;
    }

    public Integer getPrecio_editable() {
        return precio_editable;
    }

    public void setPrecio_editable(Integer precio_editable) {
        this.precio_editable = precio_editable;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ServicioBelleza [id_servicios_belleza=" + id_servicios_belleza + ", id_tenants=" + id_tenants
                + ", nombre_servicio_belleza=" + nombre_servicio_belleza + ", descripcion=" + descripcion
                + ", categoria=" + categoria + ", duracion_minima=" + duracion_minima + ", duracion_maxima="
                + duracion_maxima + ", precio_base=" + precio_base + ", precio_editable=" + precio_editable
                + ", estado=" + estado + "]";
    }
}
