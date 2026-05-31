package proyecto.lp.iii.api.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "repartidores")
@SQLDelete(sql = "UPDATE repartidores SET estado=0 WHERE id_repartidores=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_repartidores",
        "id_usuarios",
        "nombre_repartidor",
        "apellidos_repartidor",
        "numero_documento",
        "tipo_vehiculo",
        "placa_vehiculo",
        "numero_licencia",
        "estado"
})
public class Repartidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_repartidores;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuarios id_usuarios;

    private String nombre_repartidor;
    private String apellidos_repartidor;
    private String numero_documento;
    private String tipo_vehiculo;
    private String placa_vehiculo;
    private String numero_licencia;
    private Integer estado = 1;

    public Integer getId_repartidores() {
        return id_repartidores;
    }

    public void setId_repartidores(Integer id_repartidores) {
        this.id_repartidores = id_repartidores;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getNombre_repartidor() {
        return nombre_repartidor;
    }

    public void setNombre_repartidor(String nombre_repartidor) {
        this.nombre_repartidor = nombre_repartidor;
    }

    public String getApellidos_repartidor() {
        return apellidos_repartidor;
    }

    public void setApellidos_repartidor(String apellidos_repartidor) {
        this.apellidos_repartidor = apellidos_repartidor;
    }

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getNumero_licencia() {
        return numero_licencia;
    }

    public void setNumero_licencia(String numero_licencia) {
        this.numero_licencia = numero_licencia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Repartidor [id_repartidores=" + id_repartidores + ", id_usuarios=" + id_usuarios
                + ", nombre_repartidor=" + nombre_repartidor + ", apellidos_repartidor=" + apellidos_repartidor
                + ", numero_documento=" + numero_documento + ", tipo_vehiculo=" + tipo_vehiculo + ", placa_vehiculo="
                + placa_vehiculo + ", numero_licencia=" + numero_licencia + ", estado=" + estado + "]";
    }
}
