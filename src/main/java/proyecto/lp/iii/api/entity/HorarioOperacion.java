package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "horarios_operacion")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE horarios_operacion SET estado=0 WHERE id_horarios_operacion=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_horarios_operacion",
        "id_sedes",
        "dia_semana",
        "hora_apertura",
        "hora_cierre",
        "estado"
})
public class HorarioOperacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_horarios_operacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    private String dia_semana;
    private LocalTime hora_apertura;
    private LocalTime hora_cierre;
    private Integer estado = 1;

    public Integer getId_horarios_operacion() {
        return id_horarios_operacion;
    }

    public void setId_horarios_operacion(Integer id_horarios_operacion) {
        this.id_horarios_operacion = id_horarios_operacion;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public LocalTime getHora_apertura() {
        return hora_apertura;
    }

    public void setHora_apertura(LocalTime hora_apertura) {
        this.hora_apertura = hora_apertura;
    }

    public LocalTime getHora_cierre() {
        return hora_cierre;
    }

    public void setHora_cierre(LocalTime hora_cierre) {
        this.hora_cierre = hora_cierre;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "HorarioOperacion [id_horarios_operacion=" + id_horarios_operacion + ", id_sedes=" + id_sedes
                + ", dia_semana=" + dia_semana + ", hora_apertura=" + hora_apertura + ", hora_cierre=" + hora_cierre
                + ", estado=" + estado + "]";
    }
}
