package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "configuracion_global")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracionGlobal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, length = 100)
    private String clave;

    @Column(columnDefinition = "TEXT")
    private String valor;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('string', 'numerico', 'booleano', 'json') DEFAULT 'string'")
    private TipoValor tipoValor = TipoValor.STRING;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum TipoValor {
        STRING, NUMERICO, BOOLEANO, JSON
    }
}
