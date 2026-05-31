package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gastos_recurrentes")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE gastos_recurrentes SET estado=0 WHERE id_gastos_recurrentes=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_gastos_recurrentes",
        "id_tenants",
        "id_sedes",
        "concepto",
        "monto",
        "frecuencia",
        "fecha_proximo_registro",
        "estado"
})
public class GastoRecurrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_gastos_recurrentes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    private String concepto;
    private BigDecimal monto;
    private String frecuencia;
    private LocalDate fecha_proximo_registro;
    private Integer estado = 1;

    public Integer getId_gastos_recurrentes() {
        return id_gastos_recurrentes;
    }

    public void setId_gastos_recurrentes(Integer id_gastos_recurrentes) {
        this.id_gastos_recurrentes = id_gastos_recurrentes;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public LocalDate getFecha_proximo_registro() {
        return fecha_proximo_registro;
    }

    public void setFecha_proximo_registro(LocalDate fecha_proximo_registro) {
        this.fecha_proximo_registro = fecha_proximo_registro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "GastoRecurrente [id_gastos_recurrentes=" + id_gastos_recurrentes + ", id_tenants=" + id_tenants
                + ", id_sedes=" + id_sedes + ", concepto=" + concepto + ", monto=" + monto + ", frecuencia="
                + frecuencia + ", fecha_proximo_registro=" + fecha_proximo_registro + ", estado=" + estado + "]";
    }
}
