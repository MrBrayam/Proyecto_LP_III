package proyecto.lp.iii.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lotes_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "almacen_id", nullable = false)
    private Almacen almacen;

    @Column(length = 100)
    private String numeroLote;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer cantidadDisponible;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column
    private LocalDate fechaVencimiento;

    @Column
    private Integer proveedorId;

    @Column
    private LocalDate fechaIngreso;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('disponible', 'reservado', 'agotado', 'vencido') DEFAULT 'disponible'")
    private EstadoLote estado = EstadoLote.DISPONIBLE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum EstadoLote {
        DISPONIBLE, RESERVADO, AGOTADO, VENCIDO
    }
}
