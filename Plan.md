revisa todos los en entity nas espeficamente en la session 
@JsonPropertyOrder({
        "id_almacenes",
        "id_sedes",
        "nombre_almacen",
        "ubicacion",
        "capacidad",
        "estado"
})
y tambien que esten referenciadas los fk ejem:
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sedes", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Sede id_sedes;
    
    el resto del codigo deban coincidir con la base datos. si no coinciden arreglar el codigo