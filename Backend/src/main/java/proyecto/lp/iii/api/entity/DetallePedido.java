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

@Entity
@Table(name = "detalle_pedido")
@JsonPropertyOrder({
        "id_detalle_pedido",
        "id_pedidos",
        "id_productos",
        "cantidad",
        "precio_unitario",
        "descuento",
        "subtotal"
})
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedidos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pedido id_pedidos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    @Column
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precio_unitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    public Integer getId_detalle_pedido() {
        return id_detalle_pedido;
    }

    public void setId_detalle_pedido(Integer id_detalle_pedido) {
        this.id_detalle_pedido = id_detalle_pedido;
    }

    public Pedido getId_pedidos() {
        return id_pedidos;
    }

    public void setId_pedidos(Pedido id_pedidos) {
        this.id_pedidos = id_pedidos;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetallePedido [id_detalle_pedido=" + id_detalle_pedido + ", id_pedidos=" + id_pedidos
                + ", id_productos=" + id_productos + ", cantidad=" + cantidad + ", precio_unitario="
                + precio_unitario + ", descuento=" + descuento + ", subtotal=" + subtotal + "]";
    }
}
