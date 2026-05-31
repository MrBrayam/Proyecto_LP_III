package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "preferencias_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferenciaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @Column(length = 10)
    private String idioma = "es";

    @Column(length = 20)
    private String formatoFecha = "DD/MM/YYYY";

    @Column(length = 50)
    private String zonaHoraria;

    @Column(nullable = false)
    private Boolean notificacionesActivas = true;

    @Column(length = 100)
    private String vistaInicial;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('claro', 'oscuro') DEFAULT 'claro'")
    private TemaInterfaz temaInterfaz = TemaInterfaz.CLARO;

    public enum TemaInterfaz {
        CLARO, OSCURO
    }
}
