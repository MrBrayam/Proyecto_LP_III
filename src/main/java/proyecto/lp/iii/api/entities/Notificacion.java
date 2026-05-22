package proyecto.lp.iii.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('stock_bajo', 'producto_vencer', 'cita_pendiente', 'cancelacion', 'acceso_sospechoso', 'pago_vencido', 'otro') DEFAULT 'otro'")
    private TipoNotificacion tipo = TipoNotificacion.OTRO;

    @Column(length = 255)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('correo', 'sms', 'sistema', 'whatsapp') DEFAULT 'sistema'")
    private CanalNotificacion canalEnvio = CanalNotificacion.SISTEMA;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('no_leido', 'leido') DEFAULT 'no_leido'")
    private EstadoNotificacion estadoLectura = EstadoNotificacion.NO_LEIDO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column
    private LocalDateTime fechaLectura;

    public enum TipoNotificacion {
        STOCK_BAJO, PRODUCTO_VENCER, CITA_PENDIENTE, CANCELACION, ACCESO_SOSPECHOSO, PAGO_VENCIDO, OTRO
    }

    public enum CanalNotificacion {
        CORREO, SMS, SISTEMA, WHATSAPP
    }

    public enum EstadoNotificacion {
        NO_LEIDO, LEIDO
    }
}
