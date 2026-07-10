package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "lotes_inventario")
@SQLDelete(sql = "UPDATE lotes_inventario SET estado=0 WHERE id_lotes_inventario=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_lotes_inventario",
        "id_productos",
        "id_almacenes",
        "numero_lote",
        "cantidad",
        "cantidad_disponible",
        "precio_unitario",
        "fecha_vencimiento",
        "id_proveedores",
        "fecha_ingreso",
        "observaciones",
        "estado"
})
public class LoteInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_lotes_inventario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Producto id_productos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_almacenes", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Almacen id_almacenes;

    private String numero_lote;
    private Integer cantidad;
    private Integer cantidad_disponible;
    private BigDecimal precio_unitario;
    private LocalDate fecha_vencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Proveedor id_proveedores;
    private LocalDate fecha_ingreso;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private Integer estado = 1;

    public Integer getId_lotes_inventario() {
        return id_lotes_inventario;
    }

    public void setId_lotes_inventario(Integer id_lotes_inventario) {
        this.id_lotes_inventario = id_lotes_inventario;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public Almacen getId_almacenes() {
        return id_almacenes;
    }

    public void setId_almacenes(Almacen id_almacenes) {
        this.id_almacenes = id_almacenes;
    }

    public String getNumero_lote() {
        return numero_lote;
    }

    public void setNumero_lote(String numero_lote) {
        this.numero_lote = numero_lote;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(Integer cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public BigDecimal getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(BigDecimal precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Proveedor getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(Proveedor id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "LoteInventario [id_lotes_inventario=" + id_lotes_inventario + ", id_productos=" + id_productos
                + ", id_almacenes=" + id_almacenes + ", numero_lote=" + numero_lote + ", cantidad=" + cantidad
                + ", cantidad_disponible=" + cantidad_disponible + ", precio_unitario=" + precio_unitario
                + ", fecha_vencimiento=" + fecha_vencimiento + ", id_proveedores=" + id_proveedores + ", fecha_ingreso="
                + fecha_ingreso + ", observaciones=" + observaciones + ", estado=" + estado + "]";
    }
}
