package proyecto.lp.iii.api.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET estado=0 WHERE id_usuarios=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_usuarios",
        "id_tenants",
        "nombre_usuario",
        "apellidos_usuario",
        "correo",
        "numero_documento",
        "contrasenia",
        "llave_secreta",
        "access_token",
        "tipo_usuario",
        "estado"
})
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    private String nombre_usuario;
    private String apellidos_usuario;
    private String correo;
    private String numero_documento;

    @JsonIgnore
    private String contrasenia;

    @JsonIgnore
    private String llave_secreta;

    @JsonIgnore
    private String access_token;

    private String tipo_usuario;
    private Integer estado = 1;

    public Integer getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Integer id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getApellidos_usuario() {
        return apellidos_usuario;
    }

    public void setApellidos_usuario(String apellidos_usuario) {
        this.apellidos_usuario = apellidos_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getLlave_secreta() {
        return llave_secreta;
    }

    public void setLlave_secreta(String llave_secreta) {
        this.llave_secreta = llave_secreta;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuarios [id_usuarios=" + id_usuarios + ", id_tenants=" + id_tenants + ", nombre_usuario="
                + nombre_usuario + ", apellidos_usuario=" + apellidos_usuario + ", correo=" + correo
                + ", numero_documento=" + numero_documento + ", tipo_usuario=" + tipo_usuario + ", estado=" + estado
                + "]";
    }
}
