package proyecto.lp.iii.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "categorias_productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenants tenant;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 255)
    private String imagenUrl;

    @Column
    private Integer orden;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('activo', 'inactivo') DEFAULT 'activo'")
    private EstadoCategoria estado = EstadoCategoria.ACTIVO;

    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    public enum EstadoCategoria {
        ACTIVO, INACTIVO
    }
}
