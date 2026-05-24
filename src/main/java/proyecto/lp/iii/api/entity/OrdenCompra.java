package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordenes_compra")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @Column(unique = true, length = 50)
    private String numeroOrden;

    @Column
    private LocalDate fechaOrden;

    @Column
    private LocalDate fechaEntregaEstimada;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('borrador', 'pendiente', 'aprobada', 'rechazada', 'parcial', 'completa', 'cancelada') DEFAULT 'borrador'")
    private EstadoOrden estado = EstadoOrden.BORRADOR;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoTotal;

    @Column(columnDefinition = "TEXT")
    private String condicionesEntrega;

    @Column(length = 100)
    private String formaPago;

    @Column(columnDefinition = "TEXT")
    private String notas;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL)
    private List<DetalleOrdenCompra> detalles;

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoOrden {
        BORRADOR, PENDIENTE, APROBADA, RECHAZADA, PARCIAL, COMPLETA, CANCELADA
    }
}
