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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "clientes")
@SQLDelete(sql = "UPDATE clientes SET estado=0 WHERE id_clientes=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_clientes",
        "id_tenants",
        "nombre_cliente",
        "apellidos_clientes",
        "tipo_documento",
        "numero_documento",
        "telefono",
        "correo",
        "direccion",
        "distrito",
        "tipo_cliente",
        "estado",
        "fecha_registro"
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_clientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "nombre_cliente", nullable = false, length = 255)
    private String nombre_cliente;

    @Column(name = "apellidos_clientes", nullable = false, length = 255)
    private String apellidos_clientes;

    @Column(name = "tipo_documento")
    private String tipo_documento;

    @Column(name = "numero_documento", length = 20)
    private String numero_documento;

    @Column(length = 15)
    private String telefono;

    @Column(length = 100)
    private String correo;

    @Column(length = 255)
    private String direccion;

    @Column(length = 100)
    private String distrito;

    @Column(name = "tipo_cliente")
    private String tipo_cliente;

    @Column
    private Integer estado = 1;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fecha_registro;

    public Integer getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(Integer id_clientes) {
        this.id_clientes = id_clientes;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellidos_clientes() {
        return apellidos_clientes;
    }

    public void setApellidos_clientes(String apellidos_clientes) {
        this.apellidos_clientes = apellidos_clientes;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
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

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
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
        return "Cliente [id_clientes=" + id_clientes + ", id_tenants=" + id_tenants + ", nombre_cliente="
                + nombre_cliente + ", apellidos_clientes=" + apellidos_clientes + ", tipo_documento="
                + tipo_documento + ", numero_documento=" + numero_documento + ", telefono=" + telefono
                + ", correo=" + correo + ", direccion=" + direccion + ", distrito=" + distrito
                + ", tipo_cliente=" + tipo_cliente + ", estado=" + estado
                + ", fecha_registro=" + fecha_registro + "]";
    }
}
