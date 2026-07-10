package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuario_sedes")
@JsonPropertyOrder({
        "id_usuario_sedes",
        "id_usuarios",
        "id_sedes",
        "fecha_asignacion"
})
public class UsuarioSede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario_sedes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    private LocalDateTime fecha_asignacion = LocalDateTime.now();

    public Integer getId_usuario_sedes() {
        return id_usuario_sedes;
    }

    public void setId_usuario_sedes(Integer id_usuario_sedes) {
        this.id_usuario_sedes = id_usuario_sedes;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public LocalDateTime getFecha_asignacion() {
        return fecha_asignacion;
    }

    public void setFecha_asignacion(LocalDateTime fecha_asignacion) {
        this.fecha_asignacion = fecha_asignacion;
    }

    @Override
    public String toString() {
        return "UsuarioSede [id_usuario_sedes=" + id_usuario_sedes + ", id_usuarios=" + id_usuarios + ", id_sedes="
                + id_sedes + ", fecha_asignacion=" + fecha_asignacion + "]";
    }
}
