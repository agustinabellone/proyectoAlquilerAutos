package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Solicitud {

    @Id
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Encargado encargado;


    public Solicitud(Long id, Usuario usuario, Encargado encargado) {
        this.id = id;
        this.usuario = usuario;
        this.encargado = encargado;
    }

    public Solicitud() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario cliente) {
        this.usuario = usuario;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }
}
