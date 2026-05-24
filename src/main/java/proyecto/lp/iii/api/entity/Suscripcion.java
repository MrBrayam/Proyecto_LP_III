package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "suscripciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private PlanSuscripcion plan;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaProximoPago;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activa', 'suspendida', 'cancelada') DEFAULT 'activa'")
    private EstadoSuscripcion estado = EstadoSuscripcion.ACTIVA;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioContratado;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoSuscripcion {
        ACTIVA, SUSPENDIDA, CANCELADA
    }
}

