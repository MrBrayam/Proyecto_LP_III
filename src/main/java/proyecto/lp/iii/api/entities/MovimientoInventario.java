package proyecto.lp.iii.api.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteInventario lote;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimiento tipoMovimiento;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(length = 255)
    private String motivo;

    @Column(length = 100)
    private String referenciaDocumento;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaMovimiento = LocalDateTime.now();

    public enum TipoMovimiento {
        ENTRADA, SALIDA, AJUSTE, TRANSFERENCIA, MERMA, DEVOLUCION
    }
}
