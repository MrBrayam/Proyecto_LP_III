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
@Table(name = "categorias_servicios")
@SQLDelete(sql = "UPDATE categorias_servicios SET estado=0 WHERE id_categorias_servicios=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_categorias_servicios",
        "id_tenants",
        "nombre_categoria_servicio",
        "descripcion",
        "estado"
})
public class CategoriaServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categorias_servicios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "nombre_categoria_servicio", nullable = false, length = 100)
    private String nombre_categoria_servicio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column
    private Integer estado = 1;

    public Integer getId_categorias_servicios() {
        return id_categorias_servicios;
    }

    public void setId_categorias_servicios(Integer id_categorias_servicios) {
        this.id_categorias_servicios = id_categorias_servicios;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_categoria_servicio() {
        return nombre_categoria_servicio;
    }

    public void setNombre_categoria_servicio(String nombre_categoria_servicio) {
        this.nombre_categoria_servicio = nombre_categoria_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CategoriaServicio [id_categorias_servicios=" + id_categorias_servicios + ", id_tenants="
                + id_tenants + ", nombre_categoria_servicio=" + nombre_categoria_servicio + ", descripcion="
                + descripcion + ", estado=" + estado + "]";
    }
}
