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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "caja_chica")
@JsonPropertyOrder({
        "id_caja_chica",
        "id_sesiones_caja",
        "concepto",
        "monto",
        "descripcion",
        "comprobante_url",
        "id_usuarios",
        "fecha_registro"
})
public class CajaChica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_caja_chica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesiones_caja", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SesionCaja id_sesiones_caja;

    @Column(length = 255)
    private String concepto;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "comprobante_url", length = 255)
    private String comprobante_url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha_registro = LocalDateTime.now();

    public Integer getId_caja_chica() {
        return id_caja_chica;
    }

    public void setId_caja_chica(Integer id_caja_chica) {
        this.id_caja_chica = id_caja_chica;
    }

    public SesionCaja getId_sesiones_caja() {
        return id_sesiones_caja;
    }

    public void setId_sesiones_caja(SesionCaja id_sesiones_caja) {
        this.id_sesiones_caja = id_sesiones_caja;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComprobante_url() {
        return comprobante_url;
    }

    public void setComprobante_url(String comprobante_url) {
        this.comprobante_url = comprobante_url;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "CajaChica [id_caja_chica=" + id_caja_chica + ", id_sesiones_caja=" + id_sesiones_caja
                + ", concepto=" + concepto + ", monto=" + monto + ", descripcion=" + descripcion
                + ", comprobante_url=" + comprobante_url + ", id_usuarios=" + id_usuarios + ", fecha_registro="
                + fecha_registro + "]";
    }
}
