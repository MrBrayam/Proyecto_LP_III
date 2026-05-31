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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "combos_promocionales")
@SQLDelete(sql = "UPDATE combos_promocionales SET estado=0 WHERE id_combos_promocionales=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_combos_promocionales",
        "id_tenants",
        "nombre_promocion",
        "descripcion",
        "precio_combo",
        "precio_original",
        "fecha_inicio",
        "fecha_fin",
        "visible_storefront",
        "estado"
})
public class ComboPromocional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_combos_promocionales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenants", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Tenants id_tenants;

    @Column(name = "nombre_promocion", nullable = false, length = 255)
    private String nombre_promocion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio_combo", precision = 10, scale = 2)
    private BigDecimal precio_combo;

    @Column(name = "precio_original", precision = 10, scale = 2)
    private BigDecimal precio_original;

    @Column(name = "fecha_inicio")
    private LocalDate fecha_inicio;

    @Column(name = "fecha_fin")
    private LocalDate fecha_fin;

    @Column
    private Integer visible_storefront = 1;

    @Column
    private Integer estado = 1;

    public Integer getId_combos_promocionales() {
        return id_combos_promocionales;
    }

    public void setId_combos_promocionales(Integer id_combos_promocionales) {
        this.id_combos_promocionales = id_combos_promocionales;
    }

    public Tenants getId_tenants() {
        return id_tenants;
    }

    public void setId_tenants(Tenants id_tenants) {
        this.id_tenants = id_tenants;
    }

    public String getNombre_promocion() {
        return nombre_promocion;
    }

    public void setNombre_promocion(String nombre_promocion) {
        this.nombre_promocion = nombre_promocion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio_combo() {
        return precio_combo;
    }

    public void setPrecio_combo(BigDecimal precio_combo) {
        this.precio_combo = precio_combo;
    }

    public BigDecimal getPrecio_original() {
        return precio_original;
    }

    public void setPrecio_original(BigDecimal precio_original) {
        this.precio_original = precio_original;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Integer getVisible_storefront() {
        return visible_storefront;
    }

    public void setVisible_storefront(Integer visible_storefront) {
        this.visible_storefront = visible_storefront;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ComboPromocional [id_combos_promocionales=" + id_combos_promocionales + ", id_tenants="
                + id_tenants + ", nombre_promocion=" + nombre_promocion + ", descripcion=" + descripcion
                + ", precio_combo=" + precio_combo + ", precio_original=" + precio_original + ", fecha_inicio="
                + fecha_inicio + ", fecha_fin=" + fecha_fin + ", visible_storefront=" + visible_storefront
                + ", estado=" + estado + "]";
    }
}
