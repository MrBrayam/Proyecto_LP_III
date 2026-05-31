package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reclamos")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE reclamos SET estado=0 WHERE id_reclamos=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_reclamos",
        "id_tenants",
        "id_clientes",
        "id_ventas",
        "numero_reclamo",
        "canal_ingreso",
        "tipo_incidencia",
        "descripcion",
        "estado",
        "id_usuarios_responsable",
        "sla_horas",
        "fecha_vencimiento_sla",
        "solucion_aplicada",
        "id_usuarios_usuario_creacion",
        "fecha_cierre"
})
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reclamos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clientes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cliente id_clientes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta id_ventas;

    private String numero_reclamo;
    private String canal_ingreso;
    private String tipo_incidencia;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_responsable")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios_responsable;

    private Integer sla_horas;
    private LocalDateTime fecha_vencimiento_sla;

    @Column(columnDefinition = "TEXT")
    private String solucion_aplicada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_usuario_creacion")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios_usuario_creacion;

    private LocalDateTime fecha_cierre;

    public Integer getId_reclamos() {
        return id_reclamos;
    }

    public void setId_reclamos(Integer id_reclamos) {
        this.id_reclamos = id_reclamos;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Cliente getId_clientes() {
        return id_clientes;
    }

    public void setId_clientes(Cliente id_clientes) {
        this.id_clientes = id_clientes;
    }

    public Venta getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Venta id_ventas) {
        this.id_ventas = id_ventas;
    }

    public String getNumero_reclamo() {
        return numero_reclamo;
    }

    public void setNumero_reclamo(String numero_reclamo) {
        this.numero_reclamo = numero_reclamo;
    }

    public String getCanal_ingreso() {
        return canal_ingreso;
    }

    public void setCanal_ingreso(String canal_ingreso) {
        this.canal_ingreso = canal_ingreso;
    }

    public String getTipo_incidencia() {
        return tipo_incidencia;
    }

    public void setTipo_incidencia(String tipo_incidencia) {
        this.tipo_incidencia = tipo_incidencia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuarios getId_usuarios_responsable() {
        return id_usuarios_responsable;
    }

    public void setId_usuarios_responsable(Usuarios id_usuarios_responsable) {
        this.id_usuarios_responsable = id_usuarios_responsable;
    }

    public Integer getSla_horas() {
        return sla_horas;
    }

    public void setSla_horas(Integer sla_horas) {
        this.sla_horas = sla_horas;
    }

    public LocalDateTime getFecha_vencimiento_sla() {
        return fecha_vencimiento_sla;
    }

    public void setFecha_vencimiento_sla(LocalDateTime fecha_vencimiento_sla) {
        this.fecha_vencimiento_sla = fecha_vencimiento_sla;
    }

    public String getSolucion_aplicada() {
        return solucion_aplicada;
    }

    public void setSolucion_aplicada(String solucion_aplicada) {
        this.solucion_aplicada = solucion_aplicada;
    }

    public Usuarios getId_usuarios_usuario_creacion() {
        return id_usuarios_usuario_creacion;
    }

    public void setId_usuarios_usuario_creacion(Usuarios id_usuarios_usuario_creacion) {
        this.id_usuarios_usuario_creacion = id_usuarios_usuario_creacion;
    }

    public LocalDateTime getFecha_cierre() {
        return fecha_cierre;
    }

    public void setFecha_cierre(LocalDateTime fecha_cierre) {
        this.fecha_cierre = fecha_cierre;
    }

    @Override
    public String toString() {
        return "Reclamo [id_reclamos=" + id_reclamos + ", id_tenants=" + id_tenants + ", id_clientes=" + id_clientes
                + ", id_ventas=" + id_ventas + ", numero_reclamo=" + numero_reclamo + ", canal_ingreso=" + canal_ingreso
                + ", tipo_incidencia=" + tipo_incidencia + ", descripcion=" + descripcion + ", estado=" + estado
                + ", id_usuarios_responsable=" + id_usuarios_responsable + ", sla_horas=" + sla_horas
                + ", fecha_vencimiento_sla=" + fecha_vencimiento_sla + ", solucion_aplicada=" + solucion_aplicada
                + ", id_usuarios_usuario_creacion=" + id_usuarios_usuario_creacion + ", fecha_cierre=" + fecha_cierre
                + "]";
    }
}
