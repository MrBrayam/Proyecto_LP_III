package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "ventas")
@SQLDelete(sql = "UPDATE ventas SET estado=0 WHERE id_ventas=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_ventas",
        "id_tenants",
        "id_sedes",
        "id_sesiones_caja",
        "id_clientes",
        "numero_ticket",
        "comprobante_numero",
        "tipo_comprobante",
        "subtotal",
        "descuento",
        "impuesto",
        "total",
        "estado",
        "estado_sunat",
        "id_usuarios",
        "fecha_venta"
})
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ventas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Sede id_sedes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sesiones_caja", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private SesionCaja id_sesiones_caja;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Cliente id_clientes;

    private String numero_ticket;
    private String comprobante_numero;
    private String tipo_comprobante;
    private BigDecimal subtotal;
    private BigDecimal descuento = BigDecimal.ZERO;
    private BigDecimal impuesto;
    private BigDecimal total;
    private Integer estado = 1;
    private String estado_sunat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuarios id_usuarios;

    private LocalDateTime fecha_venta = LocalDateTime.now();

    public Integer getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Integer id_ventas) {
        this.id_ventas = id_ventas;
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

    public SesionCaja getId_sesiones_caja() {
        return id_sesiones_caja;
    }

    public void setId_sesiones_caja(SesionCaja id_sesiones_caja) {
        this.id_sesiones_caja = id_sesiones_caja;
    }

    public Cliente getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(Cliente id_clientes) {
        this.id_clientes = id_clientes;
    }

    public String getNumero_ticket() {
        return numero_ticket;
    }

    public void setNumero_ticket(String numero_ticket) {
        this.numero_ticket = numero_ticket;
    }

    public String getComprobante_numero() {
        return comprobante_numero;
    }

    public void setComprobante_numero(String comprobante_numero) {
        this.comprobante_numero = comprobante_numero;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(BigDecimal impuesto) {
        this.impuesto = impuesto;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getEstado_sunat() {
        return estado_sunat;
    }

    public void setEstado_sunat(String estado_sunat) {
        this.estado_sunat = estado_sunat;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public LocalDateTime getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(LocalDateTime fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    @Override
    public String toString() {
        return "Venta [id_ventas=" + id_ventas + ", id_tenants=" + id_tenants + ", id_sedes=" + id_sedes
                + ", id_sesiones_caja=" + id_sesiones_caja + ", id_clientes=" + id_clientes + ", numero_ticket="
                + numero_ticket + ", comprobante_numero=" + comprobante_numero + ", tipo_comprobante="
                + tipo_comprobante + ", subtotal=" + subtotal + ", descuento=" + descuento + ", impuesto=" + impuesto
                + ", total=" + total + ", estado=" + estado + ", estado_sunat=" + estado_sunat + ", id_usuarios="
                + id_usuarios + ", fecha_venta=" + fecha_venta + "]";
    }
}
