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
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "almacenes")
@SQLDelete(sql = "UPDATE almacenes SET estado=0 WHERE id_almacenes=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({
        "id_almacenes",
        "id_sedes",
        "nombre_almacen",
        "ubicacion",
        "capacidad",
        "estado"
})
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_almacenes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;

    @Column(name = "nombre_almacen", nullable = false, length = 100)
    private String nombre_almacen;

    @Column(length = 255)
    private String ubicacion;

    @Column
    private Integer capacidad;

    @Column
    private Integer estado = 1;

    public Integer getId_almacenes() {
        return id_almacenes;
    }

    public void setId_almacenes(Integer id_almacenes) {
        this.id_almacenes = id_almacenes;
    }

    public Sede getId_sedes() {
        return id_sedes;
    }

    public void setId_sedes(Sede id_sedes) {
        this.id_sedes = id_sedes;
    }

    public String getNombre_almacen() {
        return nombre_almacen;
    }

    public void setNombre_almacen(String nombre_almacen) {
        this.nombre_almacen = nombre_almacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Almacen [id_almacenes=" + id_almacenes + ", id_sedes=" + id_sedes + ", nombre_almacen="
                + nombre_almacen + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + ", estado=" + estado
                + "]";
    }
}
