package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "pedidos")
@SQLDelete(sql = "UPDATE pedidos SET estado=0 WHERE id_pedidos=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_pedidos",
        "id_tenants",
        "id_clientes",
        "numero_pedido",
        "modalidad",
        "estado",
        "subtotal",
        "costo_envio",
        "impuesto",
        "total",
        "direccion_entrega",
        "id_zonas_delivery",
        "id_usuarios",
        "fecha_pedido",
        "fecha_entrega_estimada",
        "fecha_entrega_real"
})
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Cliente id_clientes;

    private String numero_pedido;
    private String modalidad;
    private Integer estado = 1;
    private BigDecimal subtotal;
    private BigDecimal costo_envio = BigDecimal.ZERO;
    private BigDecimal impuesto;
    private BigDecimal total;
    private String direccion_entrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zonas_delivery")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private ZonaDelivery id_zonas_delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuarios id_usuarios;

    private LocalDateTime fecha_pedido = LocalDateTime.now();
    private LocalDateTime fecha_entrega_estimada;
    private LocalDateTime fecha_entrega_real;

    public Integer getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(Integer id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Cliente getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(Cliente id_clientes) {
        this.id_clientes = id_clientes;
    }

    public String getNumero_pedido() {
        return numero_pedido;
    }

    public void setNumero_pedido(String numero_pedido) {
        this.numero_pedido = numero_pedido;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getCosto_envio() {
        return costo_envio;
    }

    public void setCosto_envio(BigDecimal costo_envio) {
        this.costo_envio = costo_envio;
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

    public String getDireccion_entrega() {
        return direccion_entrega;
    }

    public void setDireccion_entrega(String direccion_entrega) {
        this.direccion_entrega = direccion_entrega;
    }

    public ZonaDelivery getId_zonas_delivery() {
        return id_zonas_delivery;
    }

    public void setId_zonas_delivery(ZonaDelivery id_zonas_delivery) {
        this.id_zonas_delivery = id_zonas_delivery;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public LocalDateTime getFecha_pedido() {
        return fecha_pedido;
    }

    public void setFecha_pedido(LocalDateTime fecha_pedido) {
        this.fecha_pedido = fecha_pedido;
    }

    public LocalDateTime getFecha_entrega_estimada() {
        return fecha_entrega_estimada;
    }

    public void setFecha_entrega_estimada(LocalDateTime fecha_entrega_estimada) {
        this.fecha_entrega_estimada = fecha_entrega_estimada;
    }

    public LocalDateTime getFecha_entrega_real() {
        return fecha_entrega_real;
    }

    public void setFecha_entrega_real(LocalDateTime fecha_entrega_real) {
        this.fecha_entrega_real = fecha_entrega_real;
    }

    @Override
    public String toString() {
        return "Pedido [id_pedidos=" + id_pedidos + ", id_tenants=" + id_tenants + ", id_clientes=" + id_clientes
                + ", numero_pedido=" + numero_pedido + ", modalidad=" + modalidad + ", estado=" + estado
                + ", subtotal=" + subtotal + ", costo_envio=" + costo_envio + ", impuesto=" + impuesto + ", total="
                + total + ", direccion_entrega=" + direccion_entrega + ", id_zonas_delivery=" + id_zonas_delivery
                + ", id_usuarios=" + id_usuarios + ", fecha_pedido=" + fecha_pedido + ", fecha_entrega_estimada="
                + fecha_entrega_estimada + ", fecha_entrega_real=" + fecha_entrega_real + "]";
    }
}
