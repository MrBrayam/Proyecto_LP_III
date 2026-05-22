package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, length = 255)
    private String nombreCompleto;

    @Column(nullable = false, length = 100)
    private String correo;

    @Column(length = 20)
    private String numeroDocumento;

    @Column(nullable = false)
    private String contrasenaHash;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('superadmin', 'admin', 'cajero', 'recepcionista', 'especialista', 'estilista', 'gerente', 'otro') DEFAULT 'otro'")
    private TipoUsuario tipoUsuario = TipoUsuario.OTRO;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoUsuario estado = EstadoUsuario.ACTIVO;

    @Column
    private LocalDateTime fechaUltimoAcceso;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
        name = "usuario_sedes",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "sede_id")
    )
    private List<Sede> sedesAsignadas;

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum TipoUsuario {
        SUPERADMIN, ADMIN, CAJERO, RECEPCIONISTA, ESPECIALISTA, ESTILISTA, GERENTE, OTRO
    }

    public enum EstadoUsuario {
        ACTIVO, INACTIVO
    }
}
