package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "formas_pago_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private Venta venta;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('efectivo', 'tarjeta_credito', 'tarjeta_debito', 'transferencia', 'billetera_digital', 'credito_tienda') DEFAULT 'efectivo'")
    private TipoPago tipoPago = TipoPago.EFECTIVO;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(length = 100)
    private String referenciaTransaccion;

    public enum TipoPago {
        EFECTIVO, TARJETA_CREDITO, TARJETA_DEBITO, TRANSFERENCIA, BILLETERA_DIGITAL, CREDITO_TIENDA
    }
}
