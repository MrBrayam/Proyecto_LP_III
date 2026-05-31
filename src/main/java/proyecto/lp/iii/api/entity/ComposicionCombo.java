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

@Entity
@Table(name = "composicion_combo")
@JsonPropertyOrder({
        "id_composicion_combo",
        "id_combos_promocionales",
        "id_productos",
        "cantidad"
})
public class ComposicionCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_composicion_combo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_combos_promocionales", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private ComboPromocional id_combos_promocionales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_productos", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Producto id_productos;

    @Column
    private Integer cantidad;

    public Integer getId_composicion_combo() {
        return id_composicion_combo;
    }

    public void setId_composicion_combo(Integer id_composicion_combo) {
        this.id_composicion_combo = id_composicion_combo;
    }

    public ComboPromocional getId_combos_promocionales() {
        return id_combos_promocionales;
    }

    public void setId_combos_promocionales(ComboPromocional id_combos_promocionales) {
        this.id_combos_promocionales = id_combos_promocionales;
    }

    public Producto getId_productos() {
        return id_productos;
    }

    public void setId_productos(Producto id_productos) {
        this.id_productos = id_productos;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ComposicionCombo [id_composicion_combo=" + id_composicion_combo + ", id_combos_promocionales="
                + id_combos_promocionales + ", id_productos=" + id_productos + ", cantidad=" + cantidad + "]";
    }
}
