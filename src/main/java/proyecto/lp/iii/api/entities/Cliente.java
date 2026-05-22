package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, length = 255)
    private String nombreCompleto;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DNI', 'RUC', 'Pasaporte') DEFAULT 'DNI'")
    private TipoDocumento tipoDocumento = TipoDocumento.DNI;

    @Column(length = 20)
    private String numeroDocumento;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String distrito;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('regular', 'frecuente', 'vip') DEFAULT 'regular'")
    private TipoCliente tipoCliente = TipoCliente.REGULAR;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoCliente estado = EstadoCliente.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public enum TipoDocumento {
        DNI, RUC, PASAPORTE
    }

    public enum TipoCliente {
        REGULAR, FRECUENTE, VIP
    }

    public enum EstadoCliente {
        ACTIVO, INACTIVO
    }
}
