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

@Entity
@Table(name = "detalle_devolucion_proveedor")
@JsonPropertyOrder({
        "id_detalle_devolucion_proveedor",
        "id_devoluciones_proveedor",
        "id_productos",
        "id_lotes_inventario",
        "cantidad"
})
public class DetalleDevolucionProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle_devolucion_proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_devoluciones_proveedor", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private DevolucionProveedor id_devoluciones_proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_lotes_inventario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LoteInventario id_lotes_inventario;

    @Column
    private Integer cantidad;

    public Integer getId_detalle_devolucion_proveedor() {
        return id_detalle_devolucion_proveedor;
    }

    public void setId_detalle_devolucion_proveedor(Integer id_detalle_devolucion_proveedor) {
        this.id_detalle_devolucion_proveedor = id_detalle_devolucion_proveedor;
    }

    public DevolucionProveedor getId_devoluciones_proveedor() {
        return id_devoluciones_proveedor;
    }

    public void setId_devoluciones_proveedor(DevolucionProveedor id_devoluciones_proveedor) {
        this.id_devoluciones_proveedor = id_devoluciones_proveedor;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public LoteInventario getId_lotes_inventario() {
        return id_lotes_inventario;
    }

    public void setId_lotes_inventario(LoteInventario id_lotes_inventario) {
        this.id_lotes_inventario = id_lotes_inventario;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "DetalleDevolucionProveedor [id_detalle_devolucion_proveedor=" + id_detalle_devolucion_proveedor
                + ", id_devoluciones_proveedor=" + id_devoluciones_proveedor + ", id_productos=" + id_productos
                + ", id_lotes_inventario=" + id_lotes_inventario + ", cantidad=" + cantidad + "]";
    }
}
