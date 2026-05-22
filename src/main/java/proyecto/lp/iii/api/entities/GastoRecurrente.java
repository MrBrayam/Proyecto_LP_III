package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos_recurrentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastoRecurrente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "sede_id")
    private Sede sede;

    @Column(length = 255)
    private String concepto;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('diaria', 'semanal', 'quincenal', 'mensual', 'trimestral', 'anual') DEFAULT 'mensual'")
    private Frecuencia frecuencia = Frecuencia.MENSUAL;

    @Column
    private LocalDate fechaProximoRegistro;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'pausado', 'cancelado') DEFAULT 'activo'")
    private EstadoRecurrente estado = EstadoRecurrente.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum Frecuencia {
        DIARIA, SEMANAL, QUINCENAL, MENSUAL, TRIMESTRAL, ANUAL
    }

    public enum EstadoRecurrente {
        ACTIVO, PAUSADO, CANCELADO
    }
}
