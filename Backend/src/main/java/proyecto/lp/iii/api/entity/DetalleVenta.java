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
@Table(name = "detalle_venta")
@JsonPropertyOrder({
        "id_detalle_venta",
        "id_ventas",
        "id_productos",
        "cantidad",
        "precio_unitario",
        "descuento",
        "subtotal",
        "id_lotes_inventario"
})
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta id_ventas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lotes_inventario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LoteInventario id_lotes_inventario;

    public Integer getId_detalle_venta() {
        return id_detalle_venta;
    }

    public void setId_detalle_venta(Integer id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    public Venta getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Venta id_ventas) {
        this.id_ventas = id_ventas;
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

    public LoteInventario getId_lotes_inventario() {
        return id_lotes_inventario;
    }

    public void setId_lotes_inventario(LoteInventario id_lotes_inventario) {
        this.id_lotes_inventario = id_lotes_inventario;
    }

    @Override
    public String toString() {
        return "DetalleVenta [id_detalle_venta=" + id_detalle_venta + ", id_ventas=" + id_ventas
                + ", id_productos=" + id_productos + ", cantidad=" + cantidad + ", precio_unitario="
                + precio_unitario + ", descuento=" + descuento + ", subtotal=" + subtotal
                + ", id_lotes_inventario=" + id_lotes_inventario + "]";
    }
}
