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
@Table(name = "detalle_devolucion_venta")
@JsonPropertyOrder({
        "id_detalle_devolucion_venta",
        "id_devoluciones_venta",
        "id_productos",
        "cantidad",
        "precio_unitario"
})
public class DetalleDevolucionVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_devolucion_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devoluciones_venta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DevolucionVenta id_devoluciones_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    @Column
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 10, scale = 2)
    private BigDecimal precio_unitario;

    public Integer getId_detalle_devolucion_venta() {
        return id_detalle_devolucion_venta;
    }

    public void setId_detalle_devolucion_venta(Integer id_detalle_devolucion_venta) {
        this.id_detalle_devolucion_venta = id_detalle_devolucion_venta;
    }

    public DevolucionVenta getId_devoluciones_venta() {
        return id_devoluciones_venta;
    }

    public void setId_devoluciones_venta(DevolucionVenta id_devoluciones_venta) {
        this.id_devoluciones_venta = id_devoluciones_venta;
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

    @Override
    public String toString() {
        return "DetalleDevolucionVenta [id_detalle_devolucion_venta=" + id_detalle_devolucion_venta
                + ", id_devoluciones_venta=" + id_devoluciones_venta + ", id_productos=" + id_productos
                + ", cantidad=" + cantidad + ", precio_unitario=" + precio_unitario + "]";
    }
}
