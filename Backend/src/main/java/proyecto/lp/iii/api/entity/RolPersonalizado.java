package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "roles_personalizados")
@SQLDelete(sql = "UPDATE roles_personalizados SET estado=0 WHERE id_roles_personalizados=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_roles_personalizados",
        "id_tenants",
        "nombre_rol_personalizado",
        "descripcion",
        "estado"
})
public class RolPersonalizado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_roles_personalizados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String nombre_rol_personalizado;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer estado = 1;

    public Integer getId_roles_personalizados() {
        return id_roles_personalizados;
    }

    public void setId_roles_personalizados(Integer id_roles_personalizados) {
        this.id_roles_personalizados = id_roles_personalizados;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_rol_personalizado() {
        return nombre_rol_personalizado;
    }

    public void setNombre_rol_personalizado(String nombre_rol_personalizado) {
        this.nombre_rol_personalizado = nombre_rol_personalizado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "RolPersonalizado [id_roles_personalizados=" + id_roles_personalizados + ", id_tenants=" + id_tenants
                + ", nombre_rol_personalizado=" + nombre_rol_personalizado + ", descripcion=" + descripcion
                + ", estado=" + estado + "]";
    }
}
