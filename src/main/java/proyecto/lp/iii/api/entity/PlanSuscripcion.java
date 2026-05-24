package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "planes_suscripcion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioMensual;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioTrimestral;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioAnual;

    @Enumerated(EnumType.STRING)
    private Periodicidad periodicidad;

    @Column(columnDefinition = "JSON")
    private String limitesOperativos;

    @Column(columnDefinition = "JSON")
    private String funcionalidadesHabilitadas;

    @Column(nullable = false)
    private Integer version = 1;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoPlan estado = EstadoPlan.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column
    private LocalDate fechaDesactivacion;

    public enum Periodicidad {
        MENSUAL, TRIMESTRAL, ANUAL
    }

    public enum EstadoPlan {
        ACTIVO, INACTIVO
    }
}
