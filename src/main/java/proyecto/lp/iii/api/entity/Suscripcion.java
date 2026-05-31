package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "suscripciones")
@SQLDelete(sql = "UPDATE suscripciones SET estado=0 WHERE id_suscripciones=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_suscripciones",
        "id_tenants",
        "id_planes_suscripcion",
        "fecha_inicio",
        "fecha_proximo_pago",
        "estado",
        "precio_contratado"
})
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_suscripciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planes_suscripcion", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private PlanSuscripcion id_planes_suscripcion;

    private LocalDate fecha_inicio;
    private LocalDate fecha_proximo_pago;
    private Integer estado = 1;
    private BigDecimal precio_contratado;

    public Integer getId_suscripciones() {
        return id_suscripciones;
    }

    public void setId_suscripciones(Integer id_suscripciones) {
        this.id_suscripciones = id_suscripciones;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public PlanSuscripcion getId_planes_suscripcion() {
        return id_planes_suscripcion;
    }

    public void setId_planes_suscripcion(PlanSuscripcion id_planes_suscripcion) {
        this.id_planes_suscripcion = id_planes_suscripcion;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_proximo_pago() {
        return fecha_proximo_pago;
    }

    public void setFecha_proximo_pago(LocalDate fecha_proximo_pago) {
        this.fecha_proximo_pago = fecha_proximo_pago;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public BigDecimal getPrecio_contratado() {
        return precio_contratado;
    }

    public void setPrecio_contratado(BigDecimal precio_contratado) {
        this.precio_contratado = precio_contratado;
    }

    @Override
    public String toString() {
        return "Suscripcion [id_suscripciones=" + id_suscripciones + ", id_tenants=" + id_tenants
                + ", id_planes_suscripcion=" + id_planes_suscripcion + ", fecha_inicio=" + fecha_inicio
                + ", fecha_proximo_pago=" + fecha_proximo_pago + ", estado=" + estado + ", precio_contratado="
                + precio_contratado + "]";
    }
}
