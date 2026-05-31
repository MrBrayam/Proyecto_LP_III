package proyecto.lp.iii.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "servicios_cita")
@JsonPropertyOrder({
        "id_servicios_cita",
        "id_citas",
        "id_servicios_belleza",
        "precio",
        "observaciones",
        "fecha_registro"
})
public class ServicioCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_servicios_cita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_citas", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Cita id_citas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicios_belleza", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ServicioBelleza id_servicios_belleza;

    private BigDecimal precio;

    @Column(columnDefinition = "TEXT")
    private String observaciones;

    private LocalDateTime fecha_registro = LocalDateTime.now();

    public Integer getId_servicios_cita() {
        return id_servicios_cita;
    }

    public void setId_servicios_cita(Integer id_servicios_cita) {
        this.id_servicios_cita = id_servicios_cita;
    }

    public Cita getId_citas() {
        return id_citas;
    }

    public void setId_citas(Cita id_citas) {
        this.id_citas = id_citas;
    }

    public ServicioBelleza getId_servicios_belleza() {
        return id_servicios_belleza;
    }

    public void setId_servicios_belleza(ServicioBelleza id_servicios_belleza) {
        this.id_servicios_belleza = id_servicios_belleza;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDateTime fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    @Override
    public String toString() {
        return "ServicioCita [id_servicios_cita=" + id_servicios_cita + ", id_citas=" + id_citas
                + ", id_servicios_belleza=" + id_servicios_belleza + ", precio=" + precio + ", observaciones="
                + observaciones + ", fecha_registro=" + fecha_registro + "]";
    }
}
