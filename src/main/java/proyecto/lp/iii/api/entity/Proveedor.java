package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "proveedores")
@SQLDelete(sql = "UPDATE proveedores SET estado=0 WHERE id_proveedores=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_proveedores",
        "id_tenants",
        "razon_social",
        "ruc",
        "direccion",
        "telefono",
        "correo",
        "persona_contacto",
        "terminos_comerciales",
        "categorias_productos",
        "estado"
})
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_proveedores;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    private String razon_social;
    private String ruc;
    private String direccion;
    private String telefono;
    private String correo;
    private String persona_contacto;

    @Column(columnDefinition = "TEXT")
    private String terminos_comerciales;

    private String categorias_productos;
    private Integer estado = 1;

    public Integer getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(Integer id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPersona_contacto() {
        return persona_contacto;
    }

    public void setPersona_contacto(String persona_contacto) {
        this.persona_contacto = persona_contacto;
    }

    public String getTerminos_comerciales() {
        return terminos_comerciales;
    }

    public void setTerminos_comerciales(String terminos_comerciales) {
        this.terminos_comerciales = terminos_comerciales;
    }

    public String getCategorias_productos() {
        return categorias_productos;
    }

    public void setCategorias_productos(String categorias_productos) {
        this.categorias_productos = categorias_productos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor [id_proveedores=" + id_proveedores + ", id_tenants=" + id_tenants + ", razon_social="
                + razon_social + ", ruc=" + ruc + ", direccion=" + direccion + ", telefono=" + telefono + ", correo="
                + correo + ", persona_contacto=" + persona_contacto + ", terminos_comerciales=" + terminos_comerciales
                + ", categorias_productos=" + categorias_productos + ", estado=" + estado + "]";
    }
}
