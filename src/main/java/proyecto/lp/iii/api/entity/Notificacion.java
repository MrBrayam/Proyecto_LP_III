package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@JsonPropertyOrder({
        "id_notificaciones",
        "id_tenants",
        "id_usuarios",
        "tipo",
        "titulo",
        "mensaje",
        "canal_envio",
        "estado_lectura",
        "fecha_lectura"
})
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_notificaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    private String tipo;
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    private String canal_envio;
    private String estado_lectura;
    private LocalDateTime fecha_lectura;

    public Integer getId_notificaciones() {
        return id_notificaciones;
    }

    public void setId_notificaciones(Integer id_notificaciones) {
        this.id_notificaciones = id_notificaciones;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getCanal_envio() {
        return canal_envio;
    }

    public void setCanal_envio(String canal_envio) {
        this.canal_envio = canal_envio;
    }

    public String getEstado_lectura() {
        return estado_lectura;
    }

    public void setEstado_lectura(String estado_lectura) {
        this.estado_lectura = estado_lectura;
    }

    public LocalDateTime getFecha_lectura() {
        return fecha_lectura;
    }

    public void setFecha_lectura(LocalDateTime fecha_lectura) {
        this.fecha_lectura = fecha_lectura;
    }

    @Override
    public String toString() {
        return "Notificacion [id_notificaciones=" + id_notificaciones + ", id_tenants=" + id_tenants
                + ", id_usuarios=" + id_usuarios + ", tipo=" + tipo + ", titulo=" + titulo + ", mensaje=" + mensaje
                + ", canal_envio=" + canal_envio + ", estado_lectura=" + estado_lectura + ", fecha_lectura="
                + fecha_lectura + "]";
    }
}
