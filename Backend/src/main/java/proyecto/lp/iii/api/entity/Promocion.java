package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "promociones")
@SQLDelete(sql = "UPDATE promociones SET estado=0 WHERE id_promociones=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_promociones",
        "id_tenants",
        "nombre_promocion",
        "tipo_descuento",
        "valor_descuento",
        "compra_minima",
        "fecha_inicio",
        "fecha_fin",
        "id_categorias_productos",
        "estado"
})
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_promociones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    private String nombre_promocion;
    private String tipo_descuento;
    private BigDecimal valor_descuento;
    private BigDecimal compra_minima;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorias_productos")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private CategoriaProducto id_categorias_productos;

    private Integer estado = 1;

    public Integer getId_promociones() {
        return id_promociones;
    }

    public void setId_promociones(Integer id_promociones) {
        this.id_promociones = id_promociones;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_promocion() {
        return nombre_promocion;
    }

    public void setNombre_promocion(String nombre_promocion) {
        this.nombre_promocion = nombre_promocion;
    }

    public String getTipo_descuento() {
        return tipo_descuento;
    }

    public void setTipo_descuento(String tipo_descuento) {
        this.tipo_descuento = tipo_descuento;
    }

    public BigDecimal getValor_descuento() {
        return valor_descuento;
    }

    public void setValor_descuento(BigDecimal valor_descuento) {
        this.valor_descuento = valor_descuento;
    }

    public BigDecimal getCompra_minima() {
        return compra_minima;
    }

    public void setCompra_minima(BigDecimal compra_minima) {
        this.compra_minima = compra_minima;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public CategoriaProducto getId_categorias_productos() {
        return id_categorias_productos;
    }

    public void setId_categorias_productos(CategoriaProducto id_categorias_productos) {
        this.id_categorias_productos = id_categorias_productos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Promocion [id_promociones=" + id_promociones + ", id_tenants=" + id_tenants + ", nombre_promocion="
                + nombre_promocion + ", tipo_descuento=" + tipo_descuento + ", valor_descuento=" + valor_descuento
                + ", compra_minima=" + compra_minima + ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin
                + ", id_categorias_productos=" + id_categorias_productos + ", estado=" + estado + "]";
    }
}
