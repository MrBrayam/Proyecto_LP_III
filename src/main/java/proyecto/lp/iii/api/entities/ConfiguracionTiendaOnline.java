package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "configuracion_tienda_online")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionTiendaOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(length = 255)
    private String tituloTienda;

    @Column(columnDefinition = "TEXT")
    private String descripcionTienda;

    @Column(columnDefinition = "TEXT")
    private String politicaCompra;

    @Column(columnDefinition = "TEXT")
    private String condicionesCompra;

    @Column(columnDefinition = "JSON")
    private String informacionContacto;

    @Column(nullable = false)
    private Boolean horariosCompraActivos = true;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activa', 'mantenimiento', 'desactivada') DEFAULT 'activa'")
    private EstadoTienda estadoTienda = EstadoTienda.ACTIVA;

    @Column(length = 500)
    private String mensajeEstado;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoTienda {
        ACTIVA, MANTENIMIENTO, DESACTIVADA
    }
}
