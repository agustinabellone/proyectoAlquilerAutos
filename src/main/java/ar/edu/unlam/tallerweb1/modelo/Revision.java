package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicioRevision;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFinRevision;
    private EstadoRevision estadoRevision;
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

    public LocalDate getFechaInicioRevision() {
        return fechaInicioRevision;
    }

    public void setFechaInicioRevision(LocalDate fechaInicioRevision) {
        this.fechaInicioRevision = fechaInicioRevision;
    }

    public LocalDate getFechaFinRevision() {
        return fechaFinRevision;
    }

    public void setFechaFinRevision(LocalDate fechaFinRevision) {
        this.fechaFinRevision = fechaFinRevision;
    }

    public EstadoRevision getEstadoRevision() {
        return estadoRevision;
    }

    public void setEstadoRevision(EstadoRevision estadoRevision) {
        this.estadoRevision = estadoRevision;
    }
}
