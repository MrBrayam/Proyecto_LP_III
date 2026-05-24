package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devoluciones_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevolucionVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @Column(unique = true, length = 50)
    private String numeroDevolucion;

    @Column(length = 255)
    private String motivo;

    @Enumerated(EnumType.STRING)
    private TipoResolucion tipoResolucion = TipoResolucion.REEMBOLSO;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente', 'aprobada', 'rechazada', 'completada') DEFAULT 'pendiente'")
    private EstadoDevolucion estadoDevolucion = EstadoDevolucion.PENDIENTE;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoReembolso;

    @ManyToOne
    @JoinColumn(name = "usuario_responsable_id")
    private Usuario usuarioResponsable;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaDevolucion = LocalDateTime.now();

    @OneToMany(mappedBy = "devolucion", cascade = CascadeType.ALL)
    private List<DetalleDevolucionVenta> detalles;

    public enum TipoResolucion {
        REEMBOLSO, CAMBIO_PRODUCTO, NOTA_CREDITO
    }

    public enum EstadoDevolucion {
        PENDIENTE, APROBADA, RECHAZADA, COMPLETADA
    }
}
