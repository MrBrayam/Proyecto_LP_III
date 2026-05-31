package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "repartidores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Repartidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(length = 255)
    private String nombres;

    @Column(length = 255)
    private String apellidos;

    @Column(length = 20)
    private String numeroDocumento;

    @Column(length = 100)
    private String tipoVehiculo;

    @Column(length = 20)
    private String placaVehiculo;

    @Column(length = 50)
    private String numeroLicencia;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo', 'en_ruta') DEFAULT 'activo'")
    private EstadoRepartidor estado = EstadoRepartidor.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public enum EstadoRepartidor {
        ACTIVO, INACTIVO, EN_RUTA
    }
}
