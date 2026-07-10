package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos_proveedor")
@JsonPropertyOrder({
        "id_pagos_proveedor",
        "id_cuentas_por_pagar",
        "monto_pagado",
        "forma_pago",
        "numero_comprobante",
        "fecha_pago",
        "id_usuarios",
        "observaciones"
})
public class PagoProveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pagos_proveedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuentas_por_pagar", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private CuentaPorPagar id_cuentas_por_pagar;

    private BigDecimal monto_pagado;
    private String forma_pago;
    private String numero_comprobante;
    private LocalDate fecha_pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Usuarios id_usuarios;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    public Integer getId_pagos_proveedor() {
        return id_pagos_proveedor;
    }

    public void setId_pagos_proveedor(Integer id_pagos_proveedor) {
        this.id_pagos_proveedor = id_pagos_proveedor;
    }

    public CuentaPorPagar getId_cuentas_por_pagar() {
        return id_cuentas_por_pagar;
    }

    public void setId_cuentas_por_pagar(CuentaPorPagar id_cuentas_por_pagar) {
        this.id_cuentas_por_pagar = id_cuentas_por_pagar;
    }

    public BigDecimal getMonto_pagado() {
        return monto_pagado;
    }

    public void setMonto_pagado(BigDecimal monto_pagado) {
        this.monto_pagado = monto_pagado;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public LocalDate getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDate fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public Usuarios getId_usuarios() {
        return id_usuarios;
    }

    public void setId_usuarios(Usuarios id_usuarios) {
        this.id_usuarios = id_usuarios;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "PagoProveedor [id_pagos_proveedor=" + id_pagos_proveedor + ", id_cuentas_por_pagar="
                + id_cuentas_por_pagar + ", monto_pagado=" + monto_pagado + ", forma_pago=" + forma_pago
                + ", numero_comprobante=" + numero_comprobante + ", fecha_pago=" + fecha_pago + ", id_usuarios="
                + id_usuarios + ", observaciones=" + observaciones + "]";
    }
}
