package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "metodos_pago")
@SQLDelete(sql = "UPDATE metodos_pago SET estado=0 WHERE id_metodos_pago=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_metodos_pago",
        "id_tenants",
        "nombre_metodo_de_pago",
        "tipo",
        "descripcion",
        "estado"
})
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_metodos_pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String nombre_metodo_de_pago;
    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer estado = 1;

    public Integer getId_metodos_pago() {
        return id_metodos_pago;
    }

    public void setId_metodos_pago(Integer id_metodos_pago) {
        this.id_metodos_pago = id_metodos_pago;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_metodo_de_pago() {
        return nombre_metodo_de_pago;
    }

    public void setNombre_metodo_de_pago(String nombre_metodo_de_pago) {
        this.nombre_metodo_de_pago = nombre_metodo_de_pago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        return "MetodoPago [id_metodos_pago=" + id_metodos_pago + ", id_tenants=" + id_tenants
                + ", nombre_metodo_de_pago=" + nombre_metodo_de_pago + ", tipo=" + tipo + ", descripcion="
                + descripcion + ", estado=" + estado + "]";
    }
}
