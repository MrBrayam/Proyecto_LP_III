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

@Entity
@Table(name = "configuracion_global")
@JsonPropertyOrder({
        "id_configuracion_global",
        "id_tenants",
        "clave",
        "valor",
        "tipo_valor",
        "descripcion"
})
public class ConfiguracionGlobal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_configuracion_global;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(nullable = false, length = 100)
    private String clave;

    @Column(columnDefinition = "TEXT")
    private String valor;

    @Column(name = "tipo_valor")
    private String tipo_valor;

    @Column(length = 255)
    private String descripcion;

    public Integer getId_configuracion_global() {
        return id_configuracion_global;
    }

    public void setId_configuracion_global(Integer id_configuracion_global) {
        this.id_configuracion_global = id_configuracion_global;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo_valor() {
        return tipo_valor;
    }

    public void setTipo_valor(String tipo_valor) {
        this.tipo_valor = tipo_valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ConfiguracionGlobal [id_configuracion_global=" + id_configuracion_global + ", id_tenants="
                + id_tenants + ", clave=" + clave + ", valor=" + valor + ", tipo_valor=" + tipo_valor
                + ", descripcion=" + descripcion + "]";
    }
}
