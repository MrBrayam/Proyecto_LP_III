package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @ManyToOne
    @JoinColumn(name = "sesion_caja_id", nullable = false)
    private SesionCaja sesionCaja;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(length = 50)
    private String numeroTicket;

    @Column(unique = true, length = 50)
    private String comprobanteNumero;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('boleta', 'factura', 'nota_credito', 'nota_debito') DEFAULT 'boleta'")
    private TipoComprobante tipoComprobante = TipoComprobante.BOLETA;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuento = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal impuesto;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('completada', 'anulada', 'devuelta') DEFAULT 'completada'")
    private EstadoVenta estado = EstadoVenta.COMPLETADA;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('aceptada', 'observada', 'rechazada', 'pendiente') DEFAULT 'pendiente'")
    private EstadoSunat estadoSunat = EstadoSunat.PENDIENTE;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalles;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
    private List<FormaPagoVenta> formasPago;

    public enum TipoComprobante {
        BOLETA, FACTURA, NOTA_CREDITO, NOTA_DEBITO
    }

    public enum EstadoVenta {
        COMPLETADA, ANULADA, DEVUELTA
    }

    public enum EstadoSunat {
        ACEPTADA, OBSERVADA, RECHAZADA, PENDIENTE
    }
}
