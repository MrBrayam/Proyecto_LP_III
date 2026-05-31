package proyecto.lp.iii.api.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(unique = true, length = 50)
    private String numeroPedido;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('delivery', 'pickup', 'presencial') DEFAULT 'delivery'")
    private ModalidadPedido modalidad = ModalidadPedido.DELIVERY;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('pendiente_pago', 'confirmado', 'preparacion', 'listo', 'en_camino', 'entregado', 'cancelado') DEFAULT 'pendiente_pago'")
    private EstadoPedido estado = EstadoPedido.PENDIENTE_PAGO;

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal costoEnvio = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal impuesto;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(length = 255)
    private String direccionEntrega;

    @ManyToOne
    @JoinColumn(name = "zona_delivery_id")
    private ZonaDelivery zonaDelivery;

    @ManyToOne
    @JoinColumn(name = "repartidor_id")
    private Usuarios repartidor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaPedido = LocalDateTime.now();

    @Column
    private LocalDateTime fechaEntregaEstimada;

    @Column
    private LocalDateTime fechaEntregaReal;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<DetallePedido> detalles;

    public enum ModalidadPedido {
        DELIVERY, PICKUP, PRESENCIAL
    }

    public enum EstadoPedido {
        PENDIENTE_PAGO, CONFIRMADO, PREPARACION, LISTO, EN_CAMINO, ENTREGADO, CANCELADO
    }
}
