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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "configuracion_tienda_online")
@JsonPropertyOrder({
        "id_configuracion_tienda_online",
        "id_tenants",
        "titulo_tienda",
        "descripcion_tienda",
        "politica_compra",
        "condiciones_compra",
        "informacion_contacto",
        "horarios_compra_activos",
        "estado_tienda",
        "mensaje_estado"
})
public class ConfiguracionTiendaOnline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_configuracion_tienda_online;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "titulo_tienda", length = 255)
    private String titulo_tienda;

    @Column(name = "descripcion_tienda", columnDefinition = "TEXT")
    private String descripcion_tienda;

    @Column(name = "politica_compra", columnDefinition = "TEXT")
    private String politica_compra;

    @Column(name = "condiciones_compra", columnDefinition = "TEXT")
    private String condiciones_compra;

    @Column(name = "informacion_contacto", columnDefinition = "JSON")
    private String informacion_contacto;

    @Column(name = "horarios_compra_activos")
    private Integer horarios_compra_activos = 1;

    @Column(name = "estado_tienda")
    private String estado_tienda;

    @Column(name = "mensaje_estado", length = 500)
    private String mensaje_estado;

    public Integer getId_configuracion_tienda_online() {
        return id_configuracion_tienda_online;
    }

    public void setId_configuracion_tienda_online(Integer id_configuracion_tienda_online) {
        this.id_configuracion_tienda_online = id_configuracion_tienda_online;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getTitulo_tienda() {
        return titulo_tienda;
    }

    public void setTitulo_tienda(String titulo_tienda) {
        this.titulo_tienda = titulo_tienda;
    }

    public String getDescripcion_tienda() {
        return descripcion_tienda;
    }

    public void setDescripcion_tienda(String descripcion_tienda) {
        this.descripcion_tienda = descripcion_tienda;
    }

    public String getPolitica_compra() {
        return politica_compra;
    }

    public void setPolitica_compra(String politica_compra) {
        this.politica_compra = politica_compra;
    }

    public String getCondiciones_compra() {
        return condiciones_compra;
    }

    public void setCondiciones_compra(String condiciones_compra) {
        this.condiciones_compra = condiciones_compra;
    }

    public String getInformacion_contacto() {
        return informacion_contacto;
    }

    public void setInformacion_contacto(String informacion_contacto) {
        this.informacion_contacto = informacion_contacto;
    }

    public Integer getHorarios_compra_activos() {
        return horarios_compra_activos;
    }

    public void setHorarios_compra_activos(Integer horarios_compra_activos) {
        this.horarios_compra_activos = horarios_compra_activos;
    }

    public String getEstado_tienda() {
        return estado_tienda;
    }

    public void setEstado_tienda(String estado_tienda) {
        this.estado_tienda = estado_tienda;
    }

    public String getMensaje_estado() {
        return mensaje_estado;
    }

    public void setMensaje_estado(String mensaje_estado) {
        this.mensaje_estado = mensaje_estado;
    }

    @Override
    public String toString() {
        return "ConfiguracionTiendaOnline [id_configuracion_tienda_online=" + id_configuracion_tienda_online
                + ", id_tenants=" + id_tenants + ", titulo_tienda=" + titulo_tienda + ", descripcion_tienda="
                + descripcion_tienda + ", politica_compra=" + politica_compra + ", condiciones_compra="
                + condiciones_compra + ", informacion_contacto=" + informacion_contacto
                + ", horarios_compra_activos=" + horarios_compra_activos + ", estado_tienda=" + estado_tienda
                + ", mensaje_estado=" + mensaje_estado + "]";
    }
}
