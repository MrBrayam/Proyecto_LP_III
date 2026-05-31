integrar esto.


DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id_usuarios` int(11) NOT NULL AUTO_INCREMENT,
  `id_tenants` int(11) NOT NULL,
  `nombre_completo` varchar(255) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `numero_documento` varchar(20) DEFAULT NULL,
  `contrasena_hash` varchar(255) DEFAULT NULL,
  `tipo_usuario` enum('superadmin','admin','cajero','recepcionista','especialista','estilista','gerente','otro') DEFAULT 'otro',
  `estado` tinyint(1) DEFAULT 1,
  PRIMARY KEY (`id_usuarios`),
  UNIQUE KEY `unique_correo_tenant` (`id_tenants`,`correo`),
  KEY `idx_tenant_id` (`id_tenants`),
  KEY `idx_correo` (`correo`),
  KEY `idx_estado` (`estado`),
  KEY `idx_usuario_tenant` (`id_tenants`),
  CONSTRAINT `fk_usuarios_1` FOREIGN KEY (`id_tenants`) REFERENCES `tenants` (`id_tenants`) ON DELETE CASCADE
);

pero siguiendoe esta estrucutura

@Entity
@Table(name = "cursos")
@SQLDelete(sql ="UPDATE cursos SET estado=0 WHERE idcurso=?")
@SQLRestriction("estado=1")
@JsonPropertyOrder({"idcurso","descripcion",
"estado","id_tipo","id_categoria",
"id_naturaleza"})

public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idcurso;
    private String descripcion;
    private Integer estado = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer id_tipo;

     @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer id_categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_naturaleza")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Integer id_naturaleza;

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Integer getId_naturaleza() {
        return id_naturaleza;
    }

    public void setId_naturaleza(Integer id_naturaleza) {
        this.id_naturaleza = id_naturaleza;
    }

    @Override
    public String toString() {
        return "Cursos [idcurso=" + idcurso + ", descripcion=" + descripcion + ", estado=" + estado + ", id_tipo="
                + id_tipo + ", id_categoria=" + id_categoria + ", id_naturaleza=" + id_naturaleza + "]";
    }
}