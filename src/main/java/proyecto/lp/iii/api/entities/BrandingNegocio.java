package proyecto.lp.iii.api.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "branding_negocio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandingNegocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(length = 255)
    private String logoUrl;

    @Column(length = 7)
    private String colorPrimario;

    @Column(length = 7)
    private String colorSecundario;

    @Column(length = 255)
    private String nombreVisible;

    @Column(columnDefinition = "JSON")
    private String redesSociales;

    @Column(nullable = false)
    private LocalDateTime fechaActualizacion = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }
}
