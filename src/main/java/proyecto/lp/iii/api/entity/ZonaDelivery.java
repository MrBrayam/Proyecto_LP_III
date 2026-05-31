package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "zonas_delivery")
@org.hibernate.annotations.SQLDelete(sql = "UPDATE zonas_delivery SET estado=0 WHERE id_zonas_delivery=?")
@org.hibernate.annotations.SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_zonas_delivery",
        "id_sedes",
        "nombre_zona",
        "distritos",
        "costo_fijo",
        "monto_minimo_compra",
        "tiempo_estimado_minutos",
        "estado"
})
public class ZonaDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_zonas_delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    private String nombre_zona;
    private String distritos;
    private BigDecimal costo_fijo;
    private BigDecimal monto_minimo_compra;
    private Integer tiempo_estimado_minutos;
    private Integer estado = 1;

    public Integer getId_zonas_delivery() {
        return id_zonas_delivery;
    }

    public void setId_zonas_delivery(Integer id_zonas_delivery) {
        this.id_zonas_delivery = id_zonas_delivery;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getNombre_zona() {
        return nombre_zona;
    }

    public void setNombre_zona(String nombre_zona) {
        this.nombre_zona = nombre_zona;
    }

    public String getDistritos() {
        return distritos;
    }

    public void setDistritos(String distritos) {
        this.distritos = distritos;
    }

    public BigDecimal getCosto_fijo() {
        return costo_fijo;
    }

    public void setCosto_fijo(BigDecimal costo_fijo) {
        this.costo_fijo = costo_fijo;
    }

    public BigDecimal getMonto_minimo_compra() {
        return monto_minimo_compra;
    }

    public void setMonto_minimo_compra(BigDecimal monto_minimo_compra) {
        this.monto_minimo_compra = monto_minimo_compra;
    }

    public Integer getTiempo_estimado_minutos() {
        return tiempo_estimado_minutos;
    }

    public void setTiempo_estimado_minutos(Integer tiempo_estimado_minutos) {
        this.tiempo_estimado_minutos = tiempo_estimado_minutos;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ZonaDelivery [id_zonas_delivery=" + id_zonas_delivery + ", id_sedes=" + id_sedes + ", nombre_zona="
                + nombre_zona + ", distritos=" + distritos + ", costo_fijo=" + costo_fijo + ", monto_minimo_compra="
                + monto_minimo_compra + ", tiempo_estimado_minutos=" + tiempo_estimado_minutos + ", estado=" + estado
                + "]";
    }
}
