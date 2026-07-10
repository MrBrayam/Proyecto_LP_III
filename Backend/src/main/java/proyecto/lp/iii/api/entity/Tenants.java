package proyecto.lp.iii.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity
@Table(name = "tenants")
@SQLDelete(sql = "UPDATE tenants SET estado=0 WHERE id_tenants=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
		"id_tenants",
		"razon_social",
		"ruc",
        "direccion_fiscal",
        "correo",
        "telefono",
        "nombre_comercial",
        "tipo_negocio",
        "estado",
        "fecha_registro",
})
public class Tenants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id_tenants;
    private String razon_social;
    private String ruc;
    private String direccion_fiscal;
    private String correo;
    private String telefono;
    private String nombre_comercial;
    private String tipo_negocio;
    private Integer estado = 1;
    private LocalDateTime fecha_registro = LocalDateTime.now();
    
    public Integer getId_tenants() {
        return id_tenants;
    }
    public void setId_tenants(Integer id_tenants) {
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
    public String getDireccion_fiscal() {
        return direccion_fiscal;
    }
    public void setDireccion_fiscal(String direccion_fiscal) {
        this.direccion_fiscal = direccion_fiscal;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getNombre_comercial() {
        return nombre_comercial;
    }
    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }
    public String getTipo_negocio() {
        return tipo_negocio;
    }
    public void setTipo_negocio(String tipo_negocio) {
        this.tipo_negocio = tipo_negocio;
    }
    public Integer getEstado() {
        return estado;
    }
    public void setEstado(Integer estado) {
        this.estado = estado;
    }
    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }
    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }
    
    @Override
    public String toString() {
        return "Tenant [id_tenants=" + id_tenants + ", razon_social=" + razon_social + ", ruc=" + ruc
                + ", direccion_fiscal=" + direccion_fiscal + ", correo=" + correo + ", telefono=" + telefono
                + ", nombre_comercial=" + nombre_comercial + ", tipo_negocio=" + tipo_negocio + ", estado=" + estado
                + ", fecha_registro=" + fecha_registro + "]";
    }

    
}
