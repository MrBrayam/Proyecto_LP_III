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
@Table(name = "devoluciones_venta")
@JsonPropertyOrder({
        "id_devoluciones_venta",
        "id_tenants",
        "id_ventas",
        "numero_devolucion",
        "motivo",
        "tipo_resolucion",
        "estado_devolucion",
        "monto_reembolso",
        "id_usuarios",
        "fecha_devolucion"
})
public class DevolucionVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_devoluciones_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta id_ventas;

    @Column(name = "numero_devolucion", length = 50)
    private String numero_devolucion;

    @Column(length = 255)
    private String motivo;

    @Column(name = "tipo_resolucion")
    private String tipo_resolucion;

    @Column(name = "estado_devolucion")
    private String estado_devolucion;

    @Column(name = "monto_reembolso", precision = 10, scale = 2)
    private BigDecimal monto_reembolso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    @Column(name = "fecha_devolucion")
    private LocalDateTime fecha_devolucion = LocalDateTime.now();

    public Integer getId_devoluciones_venta() {
        return id_devoluciones_venta;
    }

    public void setId_devoluciones_venta(Integer id_devoluciones_venta) {
        this.id_devoluciones_venta = id_devoluciones_venta;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Venta getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Venta id_ventas) {
        this.id_ventas = id_ventas;
    }

    public String getNumero_devolucion() {
        return numero_devolucion;
    }

    public void setNumero_devolucion(String numero_devolucion) {
        this.numero_devolucion = numero_devolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTipo_resolucion() {
        return tipo_resolucion;
    }

    public void setTipo_resolucion(String tipo_resolucion) {
        this.tipo_resolucion = tipo_resolucion;
    }

    public String getEstado_devolucion() {
        return estado_devolucion;
    }

    public void setEstado_devolucion(String estado_devolucion) {
        this.estado_devolucion = estado_devolucion;
    }

    public BigDecimal getMonto_reembolso() {
        return monto_reembolso;
    }

    public void setMonto_reembolso(BigDecimal monto_reembolso) {
        this.monto_reembolso = monto_reembolso;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public LocalDateTime getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDateTime fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    @Override
    public String toString() {
        return "DevolucionVenta [id_devoluciones_venta=" + id_devoluciones_venta + ", id_tenants=" + id_tenants
                + ", id_ventas=" + id_ventas + ", numero_devolucion=" + numero_devolucion + ", motivo=" + motivo
                + ", tipo_resolucion=" + tipo_resolucion + ", estado_devolucion=" + estado_devolucion
                + ", monto_reembolso=" + monto_reembolso + ", id_usuarios=" + id_usuarios
                + ", fecha_devolucion=" + fecha_devolucion + "]";
    }
}
