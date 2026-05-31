package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.time.LocalDate;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "series_comprobantes")
@SQLDelete(sql = "UPDATE series_comprobantes SET estado=0 WHERE id_series_comprobantes=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_series_comprobantes",
        "id_tenants",
        "tipo_comprobante",
        "punto_emision",
        "numero_serie",
        "numero_correlativo",
        "numero_proximo",
        "fecha_autorizacion",
        "fecha_vencimiento",
        "estado"
})
public class SerieComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_series_comprobantes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Tenants id_tenants;

    private String tipo_comprobante;
    private Integer punto_emision;
    private String numero_serie;
    private Integer numero_correlativo = 0;
    private Integer numero_proximo;
    private LocalDate fecha_autorizacion;
    private LocalDate fecha_vencimiento;
    private Integer estado = 1;

    public Integer getId_series_comprobantes() {
        return id_series_comprobantes;
    }

    public void setId_series_comprobantes(Integer id_series_comprobantes) {
        this.id_series_comprobantes = id_series_comprobantes;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getTipo_comprobante() {
        return tipo_comprobante;
    }

    public void setTipo_comprobante(String tipo_comprobante) {
        this.tipo_comprobante = tipo_comprobante;
    }

    public Integer getPunto_emision() {
        return punto_emision;
    }

    public void setPunto_emision(Integer punto_emision) {
        this.punto_emision = punto_emision;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public Integer getNumero_correlativo() {
        return numero_correlativo;
    }

    public void setNumero_correlativo(Integer numero_correlativo) {
        this.numero_correlativo = numero_correlativo;
    }

    public Integer getNumero_proximo() {
        return numero_proximo;
    }

    public void setNumero_proximo(Integer numero_proximo) {
        this.numero_proximo = numero_proximo;
    }

    public LocalDate getFecha_autorizacion() {
        return fecha_autorizacion;
    }

    public void setFecha_autorizacion(LocalDate fecha_autorizacion) {
        this.fecha_autorizacion = fecha_autorizacion;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SerieComprobante [id_series_comprobantes=" + id_series_comprobantes + ", id_tenants=" + id_tenants
                + ", tipo_comprobante=" + tipo_comprobante + ", punto_emision=" + punto_emision + ", numero_serie="
                + numero_serie + ", numero_correlativo=" + numero_correlativo + ", numero_proximo=" + numero_proximo
                + ", fecha_autorizacion=" + fecha_autorizacion + ", fecha_vencimiento=" + fecha_vencimiento
                + ", estado=" + estado + "]";
    }
}
