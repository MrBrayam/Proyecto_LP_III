package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "productos")
@SQLDelete(sql = "UPDATE productos SET estado=0 WHERE id_productos=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_productos",
        "id_tenants",
        "id_categorias_productos",
        "id_marcas",
        "codigo_interno",
        "codigo_barras",
        "nombre_producto",
        "descripcion",
        "tipo_producto",
        "presentacion",
        "contenido_neto",
        "img_url",
        "precio_costo",
        "precio_venta",
        "margen_ganancia",
        "stock_minimo",
        "stock_critico",
        "visible_storefront",
        "etiqueta_especial",
        "estado"
})
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_productos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorias_productos", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private CategoriaProducto id_categorias_productos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_marcas")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Marca id_marcas;

    private String codigo_interno;
    private String codigo_barras;
    private String nombre_producto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String tipo_producto;
    private String presentacion;
    private String contenido_neto;
    private String img_url;
    private BigDecimal precio_costo;
    private BigDecimal precio_venta;

    @Column(name = "margen_ganancia", insertable = false, updatable = false)
    private BigDecimal margen_ganancia;
    private Integer stock_minimo = 10;
    private Integer stock_critico = 5;
    private Integer visible_storefront = 1;
    private String etiqueta_especial;
    private Integer estado = 1;
    public Integer getId_productos() {
        return id_productos;
    }
    public void setId_productos(Integer id_productos) {
        this.id_productos = id_productos;
    }
    public Tenants getId_tenants() {
        return id_tenants;
    }
    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }
    public CategoriaProducto getId_categorias_productos() {
        return id_categorias_productos;
    }
    public void setId_categorias_productos(CategoriaProducto id_categorias_productos) {
        this.id_categorias_productos = id_categorias_productos;
    }
    public Marca getId_marcas() {
        return id_marcas;
    }
    public void setId_marcas(Marca id_marcas) {
        this.id_marcas = id_marcas;
    }
    public String getCodigo_interno() {
        return codigo_interno;
    }
    public void setCodigo_interno(String codigo_interno) {
        this.codigo_interno = codigo_interno;
    }
    public String getCodigo_barras() {
        return codigo_barras;
    }
    public void setCodigo_barras(String codigo_barras) {
        this.codigo_barras = codigo_barras;
    }
    public String getNombre_producto() {
        return nombre_producto;
    }
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getTipo_producto() {
        return tipo_producto;
    }
    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getContenido_neto() {
        return contenido_neto;
    }
    public void setContenido_neto(String contenido_neto) {
        this.contenido_neto = contenido_neto;
    }
    public String getImg_url() {
        return img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public BigDecimal getPrecio_costo() {
        return precio_costo;
    }
    public void setPrecio_costo(BigDecimal precio_costo) {
        this.precio_costo = precio_costo;
    }
    public BigDecimal getPrecio_venta() {
        return precio_venta;
    }
    public void setPrecio_venta(BigDecimal precio_venta) {
        this.precio_venta = precio_venta;
    }
    public BigDecimal getMargen_ganancia() {
        return margen_ganancia;
    }
    public void setMargen_ganancia(BigDecimal margen_ganancia) {
        this.margen_ganancia = margen_ganancia;
    }
    public Integer getStock_minimo() {
        return stock_minimo;
    }
    public void setStock_minimo(Integer stock_minimo) {
        this.stock_minimo = stock_minimo;
    }
    public Integer getStock_critico() {
        return stock_critico;
    }
    public void setStock_critico(Integer stock_critico) {
        this.stock_critico = stock_critico;
    }
    public Integer getVisible_storefront() {
        return visible_storefront;
    }
    public void setVisible_storefront(Integer visible_storefront) {
        this.visible_storefront = visible_storefront;
    }
    public String getEtiqueta_especial() {
        return etiqueta_especial;
    }
    public void setEtiqueta_especial(String etiqueta_especial) {
        this.etiqueta_especial = etiqueta_especial;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Producto [id_productos=" + id_productos + ", id_tenants=" + id_tenants + ", id_categorias_productos="
                + id_categorias_productos + ", id_marcas=" + id_marcas + ", codigo_interno=" + codigo_interno
                + ", codigo_barras=" + codigo_barras + ", nombre_producto=" + nombre_producto + ", descripcion="
                + descripcion + ", tipo_producto=" + tipo_producto + ", presentacion=" + presentacion
                + ", contenido_neto=" + contenido_neto + ", img_url=" + img_url + ", precio_costo=" + precio_costo
                + ", precio_venta=" + precio_venta + ", margen_ganancia=" + margen_ganancia + ", stock_minimo="
                + stock_minimo + ", stock_critico=" + stock_critico + ", visible_storefront=" + visible_storefront
                + ", etiqueta_especial=" + etiqueta_especial + ", estado=" + estado + "]";
    }

    
    
}
