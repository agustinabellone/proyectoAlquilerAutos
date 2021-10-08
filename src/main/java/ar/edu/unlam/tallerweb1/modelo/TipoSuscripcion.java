package ar.edu.unlam.tallerweb1.modelo;

public class TipoSuscripcion {

    private Long id;
    private String descripcion;

    public TipoSuscripcion(Long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }
    public TipoSuscripcion() {
    }

    public TipoSuscripcion(Long id_tipo) {
        this.id=id_tipo;
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
