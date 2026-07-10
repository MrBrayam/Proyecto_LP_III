package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "gastos_operativos")
@SQLDelete(sql = "UPDATE gastos_operativos SET estado=0 WHERE id_gastos_operativos=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_gastos_operativos",
        "id_tenants",
        "id_sedes",
        "concepto",
        "categoria",
        "monto",
        "descripcion",
        "id_proveedores",
        "comprobante_numero",
        "archivo_evidencia",
        "estado",
        "id_usuarios_usuario_creacion",
        "id_usuarios_usuario_aprobacion",
        "fecha_gasto"
})
public class GastoOperativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_gastos_operativos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Sede id_sedes;

    private String concepto;
    private String categoria;
    private BigDecimal monto;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Proveedor id_proveedores;

    private String comprobante_numero;
    private String archivo_evidencia;
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_usuario_creacion")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuarios id_usuarios_usuario_creacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios_usuario_aprobacion")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuarios id_usuarios_usuario_aprobacion;

    private LocalDate fecha_gasto;

    public Integer getId_gastos_operativos() {
        return id_gastos_operativos;
    }

    public void setId_gastos_operativos(Integer id_gastos_operativos) {
        this.id_gastos_operativos = id_gastos_operativos;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Proveedor getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(Proveedor id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public String getComprobante_numero() {
        return comprobante_numero;
    }

    public void setComprobante_numero(String comprobante_numero) {
        this.comprobante_numero = comprobante_numero;
    }

    public String getArchivo_evidencia() {
        return archivo_evidencia;
    }

    public void setArchivo_evidencia(String archivo_evidencia) {
        this.archivo_evidencia = archivo_evidencia;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Usuarios getId_usuarios_usuario_creacion() {
        return id_usuarios_usuario_creacion;
    }

    public void setId_usuarios_usuario_creacion(Usuarios id_usuarios_usuario_creacion) {
        this.id_usuarios_usuario_creacion = id_usuarios_usuario_creacion;
    }

    public Usuarios getId_usuarios_usuario_aprobacion() {
        return id_usuarios_usuario_aprobacion;
    }

    public void setId_usuarios_usuario_aprobacion(Usuarios id_usuarios_usuario_aprobacion) {
        this.id_usuarios_usuario_aprobacion = id_usuarios_usuario_aprobacion;
    }

    public LocalDate getFecha_gasto() {
        return fecha_gasto;
    }

    public void setFecha_gasto(LocalDate fecha_gasto) {
        this.fecha_gasto = fecha_gasto;
    }

    @Override
    public String toString() {
        return "GastoOperativo [id_gastos_operativos=" + id_gastos_operativos + ", id_tenants=" + id_tenants
                + ", id_sedes=" + id_sedes + ", concepto=" + concepto + ", categoria=" + categoria + ", monto=" + monto
                + ", descripcion=" + descripcion + ", id_proveedores=" + id_proveedores + ", comprobante_numero="
                + comprobante_numero + ", archivo_evidencia=" + archivo_evidencia + ", estado=" + estado
                + ", id_usuarios_usuario_creacion=" + id_usuarios_usuario_creacion + ", id_usuarios_usuario_aprobacion="
                + id_usuarios_usuario_aprobacion + ", fecha_gasto=" + fecha_gasto + "]";
    }
}
