package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permisos_rol")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermisoRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private RolPersonalizado rol;

    @Column(length = 100)
    private String modulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Accion accion;

    @Column(length = 100)
    private String recurso;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoPermiso estado = EstadoPermiso.ACTIVO;

    public enum Accion {
        CREAR, EDITAR, ELIMINAR, VISUALIZAR
    }

    public enum EstadoPermiso {
        ACTIVO, INACTIVO
    }
}
