package proyecto.lp.iii.api.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String razonSocial;

    @Column(nullable = false, unique = true, length = 20)
    private String ruc;

    @Column(length = 255)
    private String direccionFiscal;

    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @Column(length = 15)
    private String telefono;

    @Column(length = 255)
    private String nombreComercial;

    @Column(length = 100)
    private String tipoNegocio;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'suspendido', 'cancelado') DEFAULT 'activo'")
    private EstadoTenant estado = EstadoTenant.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public enum EstadoTenant {
        ACTIVO, SUSPENDIDO, CANCELADO
    }
}
