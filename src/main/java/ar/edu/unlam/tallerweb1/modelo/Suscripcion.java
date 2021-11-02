package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Entity
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Usuario Usuario;

    @OneToOne
    private TipoSuscripcion TipoSuscripcion;

    @Column(nullable = true)
    private Boolean Renovacion;


    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate FechaInicio;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate FechaFin;


    public Suscripcion(){

    }

    public Suscripcion(Usuario usuario, TipoSuscripcion tipoSuscripcion) {
        this.Usuario = usuario;
        this.TipoSuscripcion = tipoSuscripcion;
        this.Renovacion=false;
        this.FechaFin=null;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ar.edu.unlam.tallerweb1.modelo.Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(ar.edu.unlam.tallerweb1.modelo.Usuario usuario) {
        Usuario = usuario;
    }

    public ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion getTipoSuscripcion() {
        return TipoSuscripcion;
    }

    public void setTipoSuscripcion(ar.edu.unlam.tallerweb1.modelo.TipoSuscripcion tipoSuscripcion) {
        TipoSuscripcion = tipoSuscripcion;
    }

    public Boolean getRenovacion() {
        return Renovacion;
    }

    public void setRenovacion(Boolean renovacion) {
        Renovacion = renovacion;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

}
