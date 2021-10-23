package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TipoSuscripcion {

    @Id
    private Long id;

    @Column
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
