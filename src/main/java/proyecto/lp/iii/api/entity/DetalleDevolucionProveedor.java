package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_devolucion_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleDevolucionProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "devolucion_id", nullable = false)
    private DevolucionProveedor devolucion;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "lote_id")
    private LoteInventario lote;

    @Column(nullable = false)
    private Integer cantidad;
}
