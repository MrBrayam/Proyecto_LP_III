package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "formas_pago_venta")
@JsonPropertyOrder({
        "id_formas_pago_venta",
        "id_ventas",
        "tipo_pago",
        "monto",
        "referencia_transaccion"
})
public class FormaPagoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_formas_pago_venta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta id_ventas;

    private String tipo_pago;
    private BigDecimal monto;
    private String referencia_transaccion;

    public Integer getId_formas_pago_venta() {
        return id_formas_pago_venta;
    }

    public void setId_formas_pago_venta(Integer id_formas_pago_venta) {
        this.id_formas_pago_venta = id_formas_pago_venta;
    }

    public Venta getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Venta id_ventas) {
        this.id_ventas = id_ventas;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getReferencia_transaccion() {
        return referencia_transaccion;
    }

    public void setReferencia_transaccion(String referencia_transaccion) {
        this.referencia_transaccion = referencia_transaccion;
    }

    @Override
    public String toString() {
        return "FormaPagoVenta [id_formas_pago_venta=" + id_formas_pago_venta + ", id_ventas=" + id_ventas
                + ", tipo_pago=" + tipo_pago + ", monto=" + monto + ", referencia_transaccion=" + referencia_transaccion
                + "]";
    }
}
