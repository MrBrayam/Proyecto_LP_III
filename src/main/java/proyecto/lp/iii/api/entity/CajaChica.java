package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "caja_chica")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CajaChica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sesion_caja_id", nullable = false)
    private SesionCaja sesionCaja;

    @Column(length = 255)
    private String concepto;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 255)
    private String comprobanteUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();
}
