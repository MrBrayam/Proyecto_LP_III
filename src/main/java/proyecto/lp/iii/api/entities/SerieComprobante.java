package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "series_comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SerieComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    @Column(nullable = false)
    private Integer puntoEmision;

    @Column(nullable = false, length = 10)
    private String numeroSerie;

    @Column(nullable = false)
    private Integer numeroCorrelativo = 0;

    @Column(nullable = false)
    private Integer numeroProximo;

    @Column
    private LocalDate fechaAutorizacion;

    @Column
    private LocalDate fechaVencimiento;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activa', 'inactiva') DEFAULT 'activa'")
    private EstadoSerie estado = EstadoSerie.ACTIVA;

    public enum TipoComprobante {
        BOLETA, FACTURA, NOTA_CREDITO, NOTA_DEBITO
    }

    public enum EstadoSerie {
        ACTIVA, INACTIVA
    }
}
