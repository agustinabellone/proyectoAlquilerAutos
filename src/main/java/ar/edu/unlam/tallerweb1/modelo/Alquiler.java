package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    private Auto auto;

    @OneToOne
    private Usuario usuario;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_ingreso;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_regreso;

    public Alquiler() {}

    public Alquiler(DatosAlquiler datosAlquiler ){
        this.auto = datosAlquiler.getAuto();
        this.usuario = datosAlquiler.getUsuario();
        this.f_ingreso = datosAlquiler.getF_ingreso();
        this.f_regreso = datosAlquiler.getF_salida();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDate f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public LocalDate getF_regreso() {
        return f_regreso;
    }

    public void setF_regreso(LocalDate f_regreso) {
        this.f_regreso = f_regreso;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Usuario getCliente() {
        return usuario;
    }

    public void setCliente(Usuario cliente) {
        this.usuario = usuario;
    }
}