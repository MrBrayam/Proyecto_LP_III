package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "sedes")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE sedes SET estado=0 WHERE id_sedes=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_sedes",
        "id_tenants",
        "nombre_sede",
        "direccion",
        "distrito",
        "telefono",
        "horario_apertura",
        "horario_cierre",
        "responsable",
        "estado"
})
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_sedes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String nombre_sede;
    private String direccion;
    private String distrito;
    private String telefono;
    private LocalTime horario_apertura;
    private LocalTime horario_cierre;
    private String responsable;
    private Integer estado = 1;

    public Integer getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Integer id_sedes) {
        this.id_sedes = id_sedes;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_sede() {
        return nombre_sede;
    }

    public void setNombre_sede(String nombre_sede) {
        this.nombre_sede = nombre_sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalTime getHorario_apertura() {
        return horario_apertura;
    }

    public void setHorario_apertura(LocalTime horario_apertura) {
        this.horario_apertura = horario_apertura;
    }

    public LocalTime getHorario_cierre() {
        return horario_cierre;
    }

    public void setHorario_cierre(LocalTime horario_cierre) {
        this.horario_cierre = horario_cierre;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sede [id_sedes=" + id_sedes + ", id_tenants=" + id_tenants + ", nombre_sede=" + nombre_sede
                + ", direccion=" + direccion + ", distrito=" + distrito + ", telefono=" + telefono
                + ", horario_apertura=" + horario_apertura + ", horario_cierre=" + horario_cierre
                + ", responsable=" + responsable + ", estado=" + estado + "]";
    }
}
