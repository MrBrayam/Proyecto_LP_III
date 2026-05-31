package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "precios_plan")
@SQLDelete(sql = "UPDATE precios_plan SET estado=0 WHERE id_precios_plan=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({"id_precios_plan","id_planes_suscripcion","periodo","precio","estado"})
public class PreciosPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_precios_plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_planes_suscripcion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PlanSuscripcion id_planes_suscripcion;

    private String periodo;
    private java.math.BigDecimal precio;
    private Integer estado = 1;

    public Integer getId_precios_plan() {
        return id_precios_plan;
    }

    public void setId_precios_plan(Integer id_precios_plan) {
        this.id_precios_plan = id_precios_plan;
    }

    public PlanSuscripcion getId_planes_suscripcion() {
        return id_planes_suscripcion;
    }

    public void setId_planes_suscripcion(PlanSuscripcion id_planes_suscripcion) {
        this.id_planes_suscripcion = id_planes_suscripcion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public java.math.BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(java.math.BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
