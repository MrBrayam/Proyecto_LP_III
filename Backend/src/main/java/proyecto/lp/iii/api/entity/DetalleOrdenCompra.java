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
@Table(name = "detalle_orden_compra")
@JsonPropertyOrder({
        "id_detalle_orden_compra",
        "id_ordenes_compra",
        "id_productos",
        "cantidad_solicitada",
        "cantidad_recibida",
        "precio_unitario",
        "subtotal"
})
public class DetalleOrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_orden_compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordenes_compra", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private OrdenCompra id_ordenes_compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    @Column(name = "cantidad_solicitada")
    private Integer cantidad_solicitada;

    @Column(name = "cantidad_recibida")
    private Integer cantidad_recibida = 0;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precio_unitario;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    public Integer getId_detalle_orden_compra() {
        return id_detalle_orden_compra;
    }

    public void setId_detalle_orden_compra(Integer id_detalle_orden_compra) {
        this.id_detalle_orden_compra = id_detalle_orden_compra;
    }

    public OrdenCompra getId_ordenes_compra() {
        return id_ordenes_compra;
    }

    public void setId_ordenes_compra(OrdenCompra id_ordenes_compra) {
        this.id_ordenes_compra = id_ordenes_compra;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public Integer getCantidad_solicitada() {
        return cantidad_solicitada;
    }

    public void setCantidad_solicitada(Integer cantidad_solicitada) {
        this.cantidad_solicitada = cantidad_solicitada;
    }

    public Integer getCantidad_recibida() {
        return cantidad_recibida;
    }

    public void setCantidad_recibida(Integer cantidad_recibida) {
        this.cantidad_recibida = cantidad_recibida;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleOrdenCompra [id_detalle_orden_compra=" + id_detalle_orden_compra + ", id_ordenes_compra="
                + id_ordenes_compra + ", id_productos=" + id_productos + ", cantidad_solicitada="
                + cantidad_solicitada + ", cantidad_recibida=" + cantidad_recibida + ", precio_unitario="
                + precio_unitario + ", subtotal=" + subtotal + "]";
    }
}
