package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "usuarios")
@SQLDelete(sql = "UPDATE usuarios SET estado=0 WHERE id_usuarios=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
		"id_usuarios",
		"tenant",
		"nombre_usuario",
		"apellidos_usuario",
		"correo",
		"numero_documento",
		"contrasena",
		"tipo_usuario",
		"estado"
})

public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id_usuarios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tenants", nullable = false)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Tenants tenant;
	private String nombre_usuario;
	private String apellidos_usuario;
	private String correo;
	private String numero_documento;
	private String contrasena;
	private String tipo_usuario;
	private Integer estado = 1;
	
	public Integer getId_usuarios() {
		return id_usuarios;
	}
	public void setId_usuarios(Integer id_usuarios) {
		this.id_usuarios = id_usuarios;
	}
	public Tenants getTenants() {
		return tenant;
	}
	public void setTenants(Tenants tenant) {
		this.tenant = tenant;
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
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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
		Integer tenantId = tenant != null ? tenant.getId_tenants() : null;
		return "Usuarios [id_usuarios=" + id_usuarios + ", tenantId=" + tenantId + ", nombre_usuario="
				+ nombre_usuario + ", apellidos_usuario=" + apellidos_usuario + ", correo=" + correo
				+ ", numero_documento=" + numero_documento + ", contrasena=" + contrasena + ", tipo_usuario="
				+ tipo_usuario + ", estado=" + estado + "]";
	}

	
}

