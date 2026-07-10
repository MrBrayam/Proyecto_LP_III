package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@JsonPropertyOrder({
        "id_movimientos_inventario",
        "id_lotes_inventario",
        "tipo_movimiento",
        "cantidad",
        "id_usuarios",
        "motivo",
        "referencia_documento",
        "fecha_movimiento"
})
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movimientos_inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lotes_inventario", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LoteInventario id_lotes_inventario;

    private String tipo_movimiento;
    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    private String motivo;
    private String referencia_documento;
    private LocalDateTime fecha_movimiento = LocalDateTime.now();

    public Integer getId_movimientos_inventario() {
        return id_movimientos_inventario;
    }

    public void setId_movimientos_inventario(Integer id_movimientos_inventario) {
        this.id_movimientos_inventario = id_movimientos_inventario;
    }

    public LoteInventario getId_lotes_inventario() {
        return id_lotes_inventario;
    }

    public void setId_lotes_inventario(LoteInventario id_lotes_inventario) {
        this.id_lotes_inventario = id_lotes_inventario;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getReferencia_documento() {
        return referencia_documento;
    }

    public void setReferencia_documento(String referencia_documento) {
        this.referencia_documento = referencia_documento;
    }

    public LocalDateTime getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(LocalDateTime fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    @Override
    public String toString() {
        return "MovimientoInventario [id_movimientos_inventario=" + id_movimientos_inventario
                + ", id_lotes_inventario=" + id_lotes_inventario + ", tipo_movimiento=" + tipo_movimiento
                + ", cantidad=" + cantidad + ", id_usuarios=" + id_usuarios + ", motivo=" + motivo
                + ", referencia_documento=" + referencia_documento + ", fecha_movimiento=" + fecha_movimiento + "]";
    }
}
