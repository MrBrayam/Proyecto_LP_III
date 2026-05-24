package proyecto.lp.iii.api.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "devoluciones_proveedor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevolucionProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @Column(unique = true, length = 50)
    private String numeroDevolucion;

    @Column
    private LocalDate fechaDevolucion;

    @Column(length = 255)
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente', 'procesada', 'rechazada') DEFAULT 'pendiente'")
    private EstadoDevolucion estado = EstadoDevolucion.PENDIENTE;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @OneToMany(mappedBy = "devolucion", cascade = CascadeType.ALL)
    private List<DetalleDevolucionProveedor> detalles;

    public enum EstadoDevolucion {
        PENDIENTE, PROCESADA, RECHAZADA
    }
}
