package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "facturas_suscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturaSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "suscripcion_id", nullable = false)
    private Suscripcion suscripcion;

    @Column(unique = true, length = 50)
    private String numeroFactura;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column
    private LocalDate fechaEmision;

    @Column
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente', 'pagada', 'vencida', 'cancelada') DEFAULT 'pendiente'")
    private EstadoPago estadoPago = EstadoPago.PENDIENTE;

    @Column(length = 50)
    private String metodoPago;

    @Column
    private LocalDate fechaPago;

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime fechaCreacion = java.time.LocalDateTime.now();

    public enum EstadoPago {
        PENDIENTE, PAGADA, VENCIDA, CANCELADA
    }
}
