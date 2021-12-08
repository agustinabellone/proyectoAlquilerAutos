package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.*;

@Entity
public class Revision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Auto auto;
    @ManyToOne
    private Usuario mecanico;
    private String comentario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuto(Auto buscado) {
        this.auto = buscado;
    }

    public Auto getAuto() {
        return this.auto;
    }

    public void setUsuario(Usuario mecanico) {
        this.mecanico = mecanico;
    }

    public Usuario getUsuario() {
        return mecanico;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
