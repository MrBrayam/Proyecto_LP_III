package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "permisos_rol")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE permisos_rol SET estado=0 WHERE id_permisos_rol=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_permisos_rol",
        "id_roles_personalizados",
        "modulo",
        "accion",
        "recurso",
        "estado"
})
public class PermisoRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_permisos_rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_roles_personalizados", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private RolPersonalizado id_roles_personalizados;

    private String modulo;
    private String accion;
    private String recurso;
    private Integer estado = 1;

    public Integer getId_permisos_rol() {
        return id_permisos_rol;
    }

    public void setId_permisos_rol(Integer id_permisos_rol) {
        this.id_permisos_rol = id_permisos_rol;
    }

    public RolPersonalizado getId_roles_personalizados() {
        return id_roles_personalizados;
    }

    public void setId_roles_personalizados(RolPersonalizado id_roles_personalizados) {
        this.id_roles_personalizados = id_roles_personalizados;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "PermisoRol [id_permisos_rol=" + id_permisos_rol + ", id_roles_personalizados="
                + id_roles_personalizados + ", modulo=" + modulo + ", accion=" + accion + ", recurso=" + recurso
                + ", estado=" + estado + "]";
    }
}
