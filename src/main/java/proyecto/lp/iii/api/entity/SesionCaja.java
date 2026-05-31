package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sesiones_caja")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE sesiones_caja SET estado=0 WHERE id_sesiones_caja=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_sesiones_caja",
        "id_sedes",
        "id_usuarios",
        "fecha_apertura",
        "fecha_cierre",
        "monto_inicial",
        "monto_final",
        "diferencia",
        "estado",
        "observaciones"
})
public class SesionCaja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sesiones_caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    private LocalDateTime fecha_apertura = LocalDateTime.now();
    private LocalDateTime fecha_cierre;
    private BigDecimal monto_inicial;
    private BigDecimal monto_final;
    private BigDecimal diferencia;
    private Integer estado = 1;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public Integer getId_sesiones_caja() {
        return id_sesiones_caja;
    }

    public void setId_sesiones_caja(Integer id_sesiones_caja) {
        this.id_sesiones_caja = id_sesiones_caja;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public LocalDateTime getFecha_apertura() {
        return fecha_apertura;
    }

    public void setFecha_apertura(LocalDateTime fecha_apertura) {
        this.fecha_apertura = fecha_apertura;
    }

    public LocalDateTime getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(LocalDateTime fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    public BigDecimal getMonto_inicial() {
        return monto_inicial;
    }

    public void setMonto_inicial(BigDecimal monto_inicial) {
        this.monto_inicial = monto_inicial;
    }

    public BigDecimal getMonto_final() {
        return monto_final;
    }

    public void setMonto_final(BigDecimal monto_final) {
        this.monto_final = monto_final;
    }

    public BigDecimal getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(BigDecimal diferencia) {
        this.diferencia = diferencia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "SesionCaja [id_sesiones_caja=" + id_sesiones_caja + ", id_sedes=" + id_sedes + ", id_usuarios="
                + id_usuarios + ", fecha_apertura=" + fecha_apertura + ", fecha_cierre=" + fecha_cierre
                + ", monto_inicial=" + monto_inicial + ", monto_final=" + monto_final + ", diferencia=" + diferencia
                + ", estado=" + estado + ", observaciones=" + observaciones + "]";
    }
}
