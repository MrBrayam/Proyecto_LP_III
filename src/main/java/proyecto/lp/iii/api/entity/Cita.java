package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "citas")
@SQLDelete(sql = "UPDATE citas SET estado=0 WHERE id_citas=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_citas",
        "id_tenants",
        "id_sedes",
        "id_clientes",
        "id_usuarios_especialista",
        "id_servicios_belleza",
        "fecha_cita",
        "hora_inicio",
        "hora_fin",
        "duracion_minutos",
        "estado",
        "observaciones",
        "id_usuarios_usuario_creacion"
})
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_citas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente id_clientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_especialista")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios_especialista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicios_belleza", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ServicioBelleza id_servicios_belleza;

    @Column(name = "fecha_cita")
    private LocalDate fecha_cita;

    @Column(name = "hora_inicio")
    private LocalTime hora_inicio;

    @Column(name = "hora_fin")
    private LocalTime hora_fin;

    @Column(name = "duracion_minutos")
    private Integer duracion_minutos;

    @Column
    private Integer estado = 1;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_usuario_creacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios_usuario_creacion;

    public Integer getId_citas() {
        return id_citas;
    }

    public void setId_citas(Integer id_citas) {
        this.id_citas = id_citas;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public Cliente getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(Cliente id_clientes) {
        this.id_clientes = id_clientes;
    }

    public Usuarios getId_usuarios_especialista() {
        return id_usuarios_especialista;
    }

    public void setId_usuarios_especialista(Usuarios id_usuarios_especialista) {
        this.id_usuarios_especialista = id_usuarios_especialista;
    }

    public ServicioBelleza getId_servicios_belleza() {
        return id_servicios_belleza;
    }

    public void setId_servicios_belleza(ServicioBelleza id_servicios_belleza) {
        this.id_servicios_belleza = id_servicios_belleza;
    }

    public LocalDate getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(LocalDate fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public LocalTime getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(LocalTime hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public LocalTime getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(LocalTime hora_fin) {
        this.hora_fin = hora_fin;
    }

    public Integer getDuracion_minutos() {
        return duracion_minutos;
    }

    public void setDuracion_minutos(Integer duracion_minutos) {
        this.duracion_minutos = duracion_minutos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuarios getId_usuarios_usuario_creacion() {
        return id_usuarios_usuario_creacion;
    }

    public void setId_usuarios_usuario_creacion(Usuarios id_usuarios_usuario_creacion) {
        this.id_usuarios_usuario_creacion = id_usuarios_usuario_creacion;
    }

    @Override
    public String toString() {
        return "Cita [id_citas=" + id_citas + ", id_tenants=" + id_tenants + ", id_sedes=" + id_sedes
                + ", id_clientes=" + id_clientes + ", id_usuarios_especialista=" + id_usuarios_especialista
                + ", id_servicios_belleza=" + id_servicios_belleza + ", fecha_cita=" + fecha_cita
                + ", hora_inicio=" + hora_inicio + ", hora_fin=" + hora_fin + ", duracion_minutos="
                + duracion_minutos + ", estado=" + estado + ", observaciones=" + observaciones
                + ", id_usuarios_usuario_creacion=" + id_usuarios_usuario_creacion + "]";
    }
}
