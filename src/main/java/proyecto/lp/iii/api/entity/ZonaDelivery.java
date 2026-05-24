package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "zonas_delivery")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZonaDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sede_id", nullable = false)
    private Sede sede;

    @Column(length = 100)
    private String nombre;

    @Column(length = 500)
    private String distritos;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoFijo;

    @Column(precision = 10, scale = 2)
    private BigDecimal montoMinimoCompra;

    @Column
    private Integer tiempoEstimadoMinutos;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoZona estado = EstadoZona.ACTIVO;

    public enum EstadoZona {
        ACTIVO, INACTIVO
    }
}
