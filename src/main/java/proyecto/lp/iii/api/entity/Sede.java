package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sedes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String distrito;

    @Column(length = 15)
    private String telefono;

    @Column
    private LocalTime horarioApertura;

    @Column
    private LocalTime horarioCierre;

    @Column(length = 255)
    private String responsable;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activa', 'suspendida', 'desactivada') DEFAULT 'activa'")
    private EstadoSede estado = EstadoSede.ACTIVA;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
    private List<Almacen> almacenes;

    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL)
    private List<HorarioOperacion> horariosOperacion;

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoSede {
        ACTIVA, SUSPENDIDA, DESACTIVADA
    }
}
