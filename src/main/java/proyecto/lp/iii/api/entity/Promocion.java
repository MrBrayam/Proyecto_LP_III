package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "promociones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenants tenant;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private TipoDescuento tipoDescuento;

    @Column(precision = 10, scale = 2)
    private BigDecimal valorDescuento;

    @Column(precision = 10, scale = 2)
    private BigDecimal compraMinima;

    @Column
    private LocalDate fechaInicio;

    @Column
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private CategoriaProducto categoria;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activa', 'inactiva') DEFAULT 'activa'")
    private EstadoPromocion estado = EstadoPromocion.ACTIVA;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum TipoDescuento {
        FIJO, PORCENTUAL, DOS_POR_UNO, TRES_POR_DOS, ESCALONADO, REGALO
    }

    public enum EstadoPromocion {
        ACTIVA, INACTIVA
    }
}
