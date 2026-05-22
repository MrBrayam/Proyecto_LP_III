package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas_por_pagar")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaPorPagar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @Column(length = 50)
    private String numeroDocumento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('factura', 'boleta', 'orden_compra') DEFAULT 'factura'")
    private TipoDocumento tipoDocumento = TipoDocumento.FACTURA;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column
    private LocalDate fechaDocumento;

    @Column
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente', 'parcial', 'pagada') DEFAULT 'pendiente'")
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum TipoDocumento {
        FACTURA, BOLETA, ORDEN_COMPRA
    }

    public enum EstadoPago {
        PENDIENTE, PARCIAL, PAGADA
    }
}
