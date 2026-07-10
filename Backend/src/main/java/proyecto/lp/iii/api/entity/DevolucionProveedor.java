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
import java.time.LocalDate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "devoluciones_proveedor")
@SQLDelete(sql = "UPDATE devoluciones_proveedor SET estado=0 WHERE id_devoluciones_proveedor=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_devoluciones_proveedor",
        "id_tenants",
        "id_proveedores",
        "numero_devolucion",
        "fecha_devolucion",
        "motivo",
        "observaciones",
        "estado"
})
public class DevolucionProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_devoluciones_proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor id_proveedores;

    @Column(name = "numero_devolucion", length = 50)
    private String numero_devolucion;

    @Column(name = "fecha_devolucion")
    private LocalDate fecha_devolucion;

    @Column(length = 255)
    private String motivo;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    @Column
    private Integer estado = 1;

    public Integer getId_devoluciones_proveedor() {
        return id_devoluciones_proveedor;
    }

    public void setId_devoluciones_proveedor(Integer id_devoluciones_proveedor) {
        this.id_devoluciones_proveedor = id_devoluciones_proveedor;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public Proveedor getId_proveedores() {
        return id_proveedores;
    }

    public void setId_proveedores(Proveedor id_proveedores) {
        this.id_proveedores = id_proveedores;
    }

    public String getNumero_devolucion() {
        return numero_devolucion;
    }

    public void setNumero_devolucion(String numero_devolucion) {
        this.numero_devolucion = numero_devolucion;
    }

    public LocalDate getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(LocalDate fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DevolucionProveedor [id_devoluciones_proveedor=" + id_devoluciones_proveedor + ", id_tenants="
                + id_tenants + ", id_proveedores=" + id_proveedores + ", numero_devolucion="
                + numero_devolucion + ", fecha_devolucion=" + fecha_devolucion + ", motivo=" + motivo
                + ", observaciones=" + observaciones + ", estado=" + estado + "]";
    }
}
