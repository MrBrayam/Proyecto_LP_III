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
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cuentas_por_pagar")
@JsonPropertyOrder({
        "id_cuentas_por_pagar",
        "id_tenants",
        "id_proveedores",
        "documento_numero",
        "tipo_documento",
        "monto",
        "fecha_documento",
        "fecha_vencimiento",
        "estado_pago"
})
public class CuentaPorPagar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cuentas_por_pagar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor id_proveedores;

    @Column(name = "documento_numero", length = 50)
    private String documento_numero;

    @Column(name = "tipo_documento")
    private String tipo_documento;

    @Column(precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(name = "fecha_documento")
    private LocalDate fecha_documento;

    @Column(name = "fecha_vencimiento")
    private LocalDate fecha_vencimiento;

    @Column(name = "estado_pago")
    private String estado_pago;

    public Integer getId_cuentas_por_pagar() {
        return id_cuentas_por_pagar;
    }

    public void setId_cuentas_por_pagar(Integer id_cuentas_por_pagar) {
        this.id_cuentas_por_pagar = id_cuentas_por_pagar;
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

    public String getDocumento_numero() {
        return documento_numero;
    }

    public void setDocumento_numero(String documento_numero) {
        this.documento_numero = documento_numero;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDate getFecha_documento() {
        return fecha_documento;
    }

    public void setFecha_documento(LocalDate fecha_documento) {
        this.fecha_documento = fecha_documento;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(String estado_pago) {
        this.estado_pago = estado_pago;
    }

    @Override
    public String toString() {
        return "CuentaPorPagar [id_cuentas_por_pagar=" + id_cuentas_por_pagar + ", id_tenants=" + id_tenants
                + ", id_proveedores=" + id_proveedores + ", documento_numero=" + documento_numero
                + ", tipo_documento=" + tipo_documento + ", monto=" + monto + ", fecha_documento="
                + fecha_documento + ", fecha_vencimiento=" + fecha_vencimiento + ", estado_pago=" + estado_pago
                + "]";
    }
}
