package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "ordenes_compra")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE ordenes_compra SET estado=0 WHERE id_ordenes_compra=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_ordenes_compra",
        "id_tenants",
        "id_proveedores",
        "numero_orden",
        "fecha_orden",
        "fecha_entrega_estimada",
        "estado",
        "monto_total",
        "condiciones_entrega",
        "forma_pago",
        "notas"
})
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ordenes_compra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedores", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Proveedor id_proveedores;

    private String numero_orden;
    private LocalDate fecha_orden;
    private LocalDate fecha_entrega_estimada;
    private Integer estado = 1;
    private BigDecimal monto_total;

    @Column(columnDefinition = "TEXT")
    private String condiciones_entrega;

    private String forma_pago;

    @Column(columnDefinition = "TEXT")
    private String notas;

    public Integer getId_ordenes_compra() {
        return id_ordenes_compra;
    }

    public void setId_ordenes_compra(Integer id_ordenes_compra) {
        this.id_ordenes_compra = id_ordenes_compra;
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

    public String getNumero_orden() {
        return numero_orden;
    }

    public void setNumero_orden(String numero_orden) {
        this.numero_orden = numero_orden;
    }

    public LocalDate getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(LocalDate fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public LocalDate getFecha_entrega_estimada() {
        return fecha_entrega_estimada;
    }

    public void setFecha_entrega_estimada(LocalDate fecha_entrega_estimada) {
        this.fecha_entrega_estimada = fecha_entrega_estimada;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public BigDecimal getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(BigDecimal monto_total) {
        this.monto_total = monto_total;
    }

    public String getCondiciones_entrega() {
        return condiciones_entrega;
    }

    public void setCondiciones_entrega(String condiciones_entrega) {
        this.condiciones_entrega = condiciones_entrega;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "OrdenCompra [id_ordenes_compra=" + id_ordenes_compra + ", id_tenants=" + id_tenants
                + ", id_proveedores=" + id_proveedores + ", numero_orden=" + numero_orden + ", fecha_orden="
                + fecha_orden + ", fecha_entrega_estimada=" + fecha_entrega_estimada + ", estado=" + estado
                + ", monto_total=" + monto_total + ", condiciones_entrega=" + condiciones_entrega + ", forma_pago="
                + forma_pago + ", notas=" + notas + "]";
    }
}
