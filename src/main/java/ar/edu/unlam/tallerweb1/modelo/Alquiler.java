package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Alquiler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime f_regreso;
    private LocalDateTime f_ingreso;

    @ManyToOne
    private Auto auto;
    @OneToOne
    private Cliente cliente;

    private Estado estado; //ACTIVO O FINALIZADO


    public Alquiler() {
    }

    public Alquiler(Auto auto) {
        this.auto=auto;
    }

    public Alquiler(Auto auto, Cliente cliente) {
        this.auto=auto;
        this.cliente=cliente;
    }

    public Alquiler(DatosAlquiler DA ){
        this.f_ingreso=DA.getF_ingreso();
        this.f_regreso=DA.getF_regreso();
        this.estado=Estado.ACTIVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getF_regreso() {
        return f_regreso;
    }

    public void setF_regreso(LocalDateTime f_regreso) {
        this.f_regreso = f_regreso;
    }

    public LocalDateTime getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDateTime f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }




}
