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
import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria")
@JsonPropertyOrder({
        "id_auditoria",
        "id_tenants",
        "id_usuarios",
        "accion",
        "tabla_afectada",
        "id_registro",
        "datos_anteriores",
        "datos_nuevos",
        "ip_address",
        "fecha_hora"
})
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_auditoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    @Column(length = 255)
    private String accion;

    @Column(name = "tabla_afectada", length = 100)
    private String tabla_afectada;

    @Column(name = "id_registro")
    private Integer id_registro;

    @Column(name = "datos_anteriores", columnDefinition = "JSON")
    private String datos_anteriores;

    @Column(name = "datos_nuevos", columnDefinition = "JSON")
    private String datos_nuevos;

    @Column(name = "ip_address", length = 45)
    private String ip_address;

    @Column(name = "fecha_hora")
    private LocalDateTime fecha_hora = LocalDateTime.now();

    public Integer getId_auditoria() {
        return id_auditoria;
    }

    public void setId_auditoria(Integer id_auditoria) {
        this.id_auditoria = id_auditoria;
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla_afectada() {
        return tabla_afectada;
    }

    public void setTabla_afectada(String tabla_afectada) {
        this.tabla_afectada = tabla_afectada;
    }

    public Integer getId_registro() {
        return id_registro;
    }

    public void setId_registro(Integer id_registro) {
        this.id_registro = id_registro;
    }

    public String getDatos_anteriores() {
        return datos_anteriores;
    }

    public void setDatos_anteriores(String datos_anteriores) {
        this.datos_anteriores = datos_anteriores;
    }

    public String getDatos_nuevos() {
        return datos_nuevos;
    }

    public void setDatos_nuevos(String datos_nuevos) {
        this.datos_nuevos = datos_nuevos;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    @Override
    public String toString() {
        return "Auditoria [id_auditoria=" + id_auditoria + ", id_tenants=" + id_tenants + ", id_usuarios="
                + id_usuarios + ", accion=" + accion + ", tabla_afectada=" + tabla_afectada + ", id_registro="
                + id_registro + ", datos_anteriores=" + datos_anteriores + ", datos_nuevos=" + datos_nuevos
                + ", ip_address=" + ip_address + ", fecha_hora=" + fecha_hora + "]";
    }
}
