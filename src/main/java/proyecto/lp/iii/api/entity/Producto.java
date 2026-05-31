package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenants tenant;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private CategoriaProducto categoria;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Column(length = 50)
    private String codigoInterno;

    @Column(unique = true, length = 100)
    private String codigoBarras;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String tipoProducto;

    @Column(length = 100)
    private String presentacion;

    @Column(length = 50)
    private String contenidoNeto;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioCosto;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @Column(precision = 5, scale = 2)
    private BigDecimal margenGanancia;

    @Column(nullable = false)
    private Integer stockMinimo = 10;

    @Column(nullable = false)
    private Integer stockCritico = 5;

    @Column(nullable = false)
    private Boolean visibleStorefront = true;

    @Column(length = 100)
    private String etiquetaEspecial;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoProducto estado = EstadoProducto.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<ImagenProducto> imagenes;

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoProducto {
        ACTIVO, INACTIVO
    }
}
