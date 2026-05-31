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
@Table(name = "branding_negocio")
@JsonPropertyOrder({
        "id_branding_negocio",
        "id_tenants",
        "logo_url",
        "color_primario",
        "color_secundario",
        "nombre_visible",
        "redes_sociales"
})
public class BrandingNegocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_branding_negocio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "logo_url", length = 255)
    private String logo_url;

    @Column(name = "color_primario", length = 7)
    private String color_primario;

    @Column(name = "color_secundario", length = 7)
    private String color_secundario;

    @Column(name = "nombre_visible", length = 255)
    private String nombre_visible;

    @Column(name = "redes_sociales", columnDefinition = "JSON")
    private String redes_sociales;

    public Integer getId_branding_negocio() {
        return id_branding_negocio;
    }

    public void setId_branding_negocio(Integer id_branding_negocio) {
        this.id_branding_negocio = id_branding_negocio;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getColor_primario() {
        return color_primario;
    }

    public void setColor_primario(String color_primario) {
        this.color_primario = color_primario;
    }

    public String getColor_secundario() {
        return color_secundario;
    }

    public void setColor_secundario(String color_secundario) {
        this.color_secundario = color_secundario;
    }

    public String getNombre_visible() {
        return nombre_visible;
    }

    public void setNombre_visible(String nombre_visible) {
        this.nombre_visible = nombre_visible;
    }

    public String getRedes_sociales() {
        return redes_sociales;
    }

    public void setRedes_sociales(String redes_sociales) {
        this.redes_sociales = redes_sociales;
    }

    @Override
    public String toString() {
        return "BrandingNegocio [id_branding_negocio=" + id_branding_negocio + ", id_tenants=" + id_tenants
                + ", logo_url=" + logo_url + ", color_primario=" + color_primario + ", color_secundario="
                + color_secundario + ", nombre_visible=" + nombre_visible + ", redes_sociales=" + redes_sociales
                + "]";
    }
}
