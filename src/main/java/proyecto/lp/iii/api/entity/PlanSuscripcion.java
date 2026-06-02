package proyecto.lp.iii.api.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "planes_suscripcion")
@SQLDelete(sql = "UPDATE planes_suscripcion SET estado=0 WHERE id_planes_suscripcion=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_planes_suscripcion",
        "nombre_plan_suscripcion",
        "descripcion",
        "estado"
})
public class PlanSuscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_planes_suscripcion;

    private String nombre_plan_suscripcion;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer estado = 1;

    public Integer getId_planes_suscripcion() {
        return id_planes_suscripcion;
    }

    public void setId_planes_suscripcion(Integer id_planes_suscripcion) {
        this.id_planes_suscripcion = id_planes_suscripcion;
    }

    public String getNombre_plan_suscripcion() {
        return nombre_plan_suscripcion;
    }

    public void setNombre_plan_suscripcion(String nombre_plan_suscripcion) {
        this.nombre_plan_suscripcion = nombre_plan_suscripcion;
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
        return "PlanSuscripcion [id_planes_suscripcion=" + id_planes_suscripcion + ", nombre_plan_suscripcion="
                + nombre_plan_suscripcion + ", descripcion=" + descripcion + ", estado=" + estado + "]";
    }
}
