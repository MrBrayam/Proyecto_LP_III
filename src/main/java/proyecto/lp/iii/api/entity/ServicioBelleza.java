package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicios_belleza")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicioBelleza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 100)
    private String categoria;

    @Column
    private Integer duracionMinima;

    @Column
    private Integer duracionMaxima;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(nullable = false)
    private Boolean precioEditable = false;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoServicio estado = EstadoServicio.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum EstadoServicio {
        ACTIVO, INACTIVO
    }
}
