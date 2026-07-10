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
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes_electronicos")
@JsonPropertyOrder({
        "id_comprobantes_electronicos",
        "id_tenants",
        "tipo_comprobante",
        "numero_serie",
        "numero_comprobante",
        "id_ventas",
        "estado_sunat",
        "respuesta_sunat",
        "fecha_envio_sunat",
        "xml_generado",
        "cdr_recibida"
})
public class ComprobanteElectronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_comprobantes_electronicos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "tipo_comprobante", nullable = false)
    private String tipo_comprobante;

    @Column(name = "numero_serie", length = 10)
    private String numero_serie;

    @Column(name = "numero_comprobante", length = 10)
    private String numero_comprobante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ventas")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Venta id_ventas;

    @Column(name = "estado_sunat")
    private String estado_sunat;

    @Column(name = "respuesta_sunat", columnDefinition = "TEXT")
    private String respuesta_sunat;

    @Column(name = "fecha_envio_sunat")
    private LocalDateTime fecha_envio_sunat;

    @Column(name = "xml_generado", columnDefinition = "LONGTEXT")
    private String xml_generado;

    @Column(name = "cdr_recibida", columnDefinition = "LONGTEXT")
    private String cdr_recibida;

    public Integer getId_comprobantes_electronicos() {
        return id_comprobantes_electronicos;
    }

    public void setId_comprobantes_electronicos(Integer id_comprobantes_electronicos) {
        this.id_comprobantes_electronicos = id_comprobantes_electronicos;
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

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public String getNumero_comprobante() {
        return numero_comprobante;
    }

    public void setNumero_comprobante(String numero_comprobante) {
        this.numero_comprobante = numero_comprobante;
    }

    public Venta getId_ventas() {
        return id_ventas;
    }

    public void setId_ventas(Venta id_ventas) {
        this.id_ventas = id_ventas;
    }

    public String getEstado_sunat() {
        return estado_sunat;
    }

    public void setEstado_sunat(String estado_sunat) {
        this.estado_sunat = estado_sunat;
    }

    public String getRespuesta_sunat() {
        return respuesta_sunat;
    }

    public void setRespuesta_sunat(String respuesta_sunat) {
        this.respuesta_sunat = respuesta_sunat;
    }

    public LocalDateTime getFecha_envio_sunat() {
        return fecha_envio_sunat;
    }

    public void setFecha_envio_sunat(LocalDateTime fecha_envio_sunat) {
        this.fecha_envio_sunat = fecha_envio_sunat;
    }

    public String getXml_generado() {
        return xml_generado;
    }

    public void setXml_generado(String xml_generado) {
        this.xml_generado = xml_generado;
    }

    public String getCdr_recibida() {
        return cdr_recibida;
    }

    public void setCdr_recibida(String cdr_recibida) {
        this.cdr_recibida = cdr_recibida;
    }

    @Override
    public String toString() {
        return "ComprobanteElectronico [id_comprobantes_electronicos=" + id_comprobantes_electronicos + ", id_tenants="
                + id_tenants + ", tipo_comprobante=" + tipo_comprobante + ", numero_serie=" + numero_serie
                + ", numero_comprobante=" + numero_comprobante + ", id_ventas=" + id_ventas + ", estado_sunat="
                + estado_sunat + ", respuesta_sunat=" + respuesta_sunat + ", fecha_envio_sunat=" + fecha_envio_sunat
                + ", xml_generado=" + xml_generado + ", cdr_recibida=" + cdr_recibida + "]";
    }
}
