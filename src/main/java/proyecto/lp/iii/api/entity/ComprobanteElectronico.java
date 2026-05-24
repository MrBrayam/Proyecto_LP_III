package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes_electronicos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteElectronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoComprobante tipoComprobante;

    @Column(length = 10)
    private String numeroSerie;

    @Column(length = 10)
    private String numeroComprobante;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('aceptada', 'observada', 'rechazada', 'pendiente_envio') DEFAULT 'pendiente_envio'")
    private EstadoSunat estadoSunat = EstadoSunat.PENDIENTE_ENVIO;

    @Column(columnDefinition = "TEXT")
    private String respuestaSunat;

    @Column
    private LocalDateTime fechaEnvioSunat;

    @Column(columnDefinition = "LONGTEXT")
    private String xmlGenerado;

    @Column(columnDefinition = "LONGTEXT")
    private String cdrRecibida;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum TipoComprobante {
        BOLETA, FACTURA, NOTA_CREDITO, NOTA_DEBITO
    }

    public enum EstadoSunat {
        ACEPTADA, OBSERVADA, RECHAZADA, PENDIENTE_ENVIO
    }
}
