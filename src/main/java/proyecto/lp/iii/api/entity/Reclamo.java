package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "reclamos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @Column(unique = true, length = 50)
    private String numeroReclamo;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('presencial', 'web', 'telefono', 'whatsapp') DEFAULT 'presencial'")
    private CanalIngreso canalIngreso = CanalIngreso.PRESENCIAL;

    @Column(length = 100)
    private String tipoIncidencia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('abierto', 'en_proceso', 'resuelto', 'cerrado') DEFAULT 'abierto'")
    private EstadoReclamo estado = EstadoReclamo.ABIERTO;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Usuario responsable;

    @Column
    private Integer slaHoras;

    @Column
    private LocalDateTime fechaVencimientoSla;

    @Column(columnDefinition = "TEXT")
    private String solucionAplicada;

    @ManyToOne
    @JoinColumn(name = "usuario_creacion_id")
    private Usuario usuarioCreacion;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column
    private LocalDateTime fechaCierre;

    public enum CanalIngreso {
        PRESENCIAL, WEB, TELEFONO, WHATSAPP
    }

    public enum EstadoReclamo {
        ABIERTO, EN_PROCESO, RESUELTO, CERRADO
    }
}
