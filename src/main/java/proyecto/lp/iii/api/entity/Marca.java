package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "marcas")
@SQLDelete(sql = "UPDATE marcas SET estado=0 WHERE id_marcas=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_marcas",
        "id_tenants",
        "nombre_marca",
        "pais_origen",
        "logo_url",
        "descripcion",
        "estado"
})
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_marcas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String nombre_marca;
    private String pais_origen;
    private String logo_url;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer estado = 1;

    public Integer getId_marcas() {
        return id_marcas;
    }

    public void setId_marcas(Integer id_marcas) {
        this.id_marcas = id_marcas;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_marca() {
        return nombre_marca;
    }

    public void setNombre_marca(String nombre_marca) {
        this.nombre_marca = nombre_marca;
    }

    public String getPais_origen() {
        return pais_origen;
    }

    public void setPais_origen(String pais_origen) {
        this.pais_origen = pais_origen;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
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
        return "Marca [id_marcas=" + id_marcas + ", id_tenants=" + id_tenants + ", nombre_marca=" + nombre_marca
                + ", pais_origen=" + pais_origen + ", logo_url=" + logo_url + ", descripcion=" + descripcion
                + ", estado=" + estado + "]";
    }
}
