package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas_suscripcion")
@JsonPropertyOrder({
        "id_facturas_suscripcion",
        "id_suscripciones",
        "numero_factura",
        "monto",
        "fecha_emision",
        "fecha_vencimiento",
        "estado_pago",
        "metodo_pago",
        "fecha_pago"
})
public class FacturaSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_facturas_suscripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_suscripciones", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Suscripcion id_suscripciones;

    private String numero_factura;
    private BigDecimal monto;
    private LocalDate fecha_emision;
    private LocalDate fecha_vencimiento;
    private String estado_pago;
    private String metodo_pago;
    private LocalDateTime fecha_pago;

    public Integer getId_facturas_suscripcion() {
        return id_facturas_suscripcion;
    }

    public void setId_facturas_suscripcion(Integer id_facturas_suscripcion) {
        this.id_facturas_suscripcion = id_facturas_suscripcion;
    }

    public Suscripcion getId_suscripciones() {
        return id_suscripciones;
    }

    public void setId_suscripciones(Suscripcion id_suscripciones) {
        this.id_suscripciones = id_suscripciones;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(LocalDate fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    @Override
    public String toString() {
        return "FacturaSuscripcion [id_facturas_suscripcion=" + id_facturas_suscripcion + ", id_suscripciones="
                + id_suscripciones + ", numero_factura=" + numero_factura + ", monto=" + monto + ", fecha_emision="
                + fecha_emision + ", fecha_vencimiento=" + fecha_vencimiento + ", estado_pago=" + estado_pago
                + ", metodo_pago=" + metodo_pago + ", fecha_pago=" + fecha_pago + "]";
    }
}
