package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos_operativos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoOperativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenants tenant;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Column(length = 255)
    private String concepto;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('insumos', 'cosmeticos', 'publicidad', 'servicios_basicos', 'mantenimiento', 'transporte', 'personal', 'otro') DEFAULT 'otro'")
    private CategoriaGasto categoria = CategoriaGasto.OTRO;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @Column(length = 50)
    private String comprobanteNumero;

    @Column(length = 255)
    private String archivoEvidencia;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('registrado', 'aprobado', 'rechazado') DEFAULT 'registrado'")
    private EstadoGasto estado = EstadoGasto.REGISTRADO;

    @ManyToOne
    @JoinColumn(name = "usuario_creacion_id")
    private Usuarios usuarioCreacion;

    @ManyToOne
    @JoinColumn(name = "usuario_aprobacion_id")
    private Usuarios usuarioAprobacion;

    @Column
    private LocalDate fechaGasto;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum CategoriaGasto {
        INSUMOS, COSMETICOS, PUBLICIDAD, SERVICIOS_BASICOS, MANTENIMIENTO, TRANSPORTE, PERSONAL, OTRO
    }

    public enum EstadoGasto {
        REGISTRADO, APROBADO, RECHAZADO
    }
}
