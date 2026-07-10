package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

@Entity
@Table(name = "preferencias_usuario")
@JsonPropertyOrder({
        "id_preferencias_usuario",
        "id_usuarios",
        "idioma",
        "formato_fecha",
        "zona_horaria",
        "notificaciones_activas",
        "vista_inicial",
        "tema_interfaz"
})
public class PreferenciaUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_preferencias_usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    private String idioma = "es";
    private String formato_fecha = "DD/MM/YYYY";
    private String zona_horaria;
    private Integer notificaciones_activas = 1;
    private String vista_inicial;
    private String tema_interfaz = "claro";

    public Integer getId_preferencias_usuario() {
        return id_preferencias_usuario;
    }

    public void setId_preferencias_usuario(Integer id_preferencias_usuario) {
        this.id_preferencias_usuario = id_preferencias_usuario;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getFormato_fecha() {
        return formato_fecha;
    }

    public void setFormato_fecha(String formato_fecha) {
        this.formato_fecha = formato_fecha;
    }

    public String getZona_horaria() {
        return zona_horaria;
    }

    public void setZona_horaria(String zona_horaria) {
        this.zona_horaria = zona_horaria;
    }

    public Integer getNotificaciones_activas() {
        return notificaciones_activas;
    }

    public void setNotificaciones_activas(Integer notificaciones_activas) {
        this.notificaciones_activas = notificaciones_activas;
    }

    public String getVista_inicial() {
        return vista_inicial;
    }

    public void setVista_inicial(String vista_inicial) {
        this.vista_inicial = vista_inicial;
    }

    public String getTema_interfaz() {
        return tema_interfaz;
    }

    public void setTema_interfaz(String tema_interfaz) {
        this.tema_interfaz = tema_interfaz;
    }

    @Override
    public String toString() {
        return "PreferenciaUsuario [id_preferencias_usuario=" + id_preferencias_usuario + ", id_usuarios=" + id_usuarios
                + ", idioma=" + idioma + ", formato_fecha=" + formato_fecha + ", zona_horaria=" + zona_horaria
                + ", notificaciones_activas=" + notificaciones_activas + ", vista_inicial=" + vista_inicial
                + ", tema_interfaz=" + tema_interfaz + "]";
    }
}
