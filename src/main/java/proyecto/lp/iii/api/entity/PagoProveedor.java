package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cuenta_por_pagar_id", nullable = false)
    private CuentaPorPagar cuentaPorPagar;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoPagado;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('efectivo', 'transferencia', 'tarjeta', 'otro') DEFAULT 'efectivo'")
    private FormaPago formaPago = FormaPago.EFECTIVO;

    @Column(length = 50)
    private String numeroComprobante;

    @Column
    private LocalDate fechaPago;

    @ManyToOne
    @JoinColumn(name = "usuario_responsable_id")
    private Usuario usuarioResponsable;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum FormaPago {
        EFECTIVO, TRANSFERENCIA, TARJETA, OTRO
    }
}
