package ar.edu.unlam.tallerweb1.modelo;


import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.*;

import java.time.LocalDate;


@Entity
public class Alquiler {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate f_egreso;
    private LocalDate f_ingreso;
    private Float adicionalCambioLugarFecha = 0.0f;
    private Float adicionalInfraccionesOtro = 0.0f;
    @ManyToOne
    private Auto auto;
    @ManyToOne
    public Usuario usuario;
    @ManyToOne
    private Garage garagePartida;
    @ManyToOne
    private Garage garageLlegadaEst;

    @ManyToOne
    private Garage garageLlegada;

    @ManyToOne
    private Encargado encargado;
    private Estado estado; //ACTIVO O FINALIZADO


    public Alquiler() {
        this.estado = Estado.ACTIVO;
    }


    public Alquiler(Long id, Auto auto) {
        this.id = id;
        this.auto = auto;
        this.estado = Estado.ACTIVO;
    }

    public Alquiler(Auto auto, Usuario usuario) {
        this.auto = auto;
        this.usuario = usuario;
        this.estado = Estado.ACTIVO;
    }

    public Alquiler(DatosAlquiler datosAlquiler) {
        this.auto = datosAlquiler.getAuto();
        this.usuario = datosAlquiler.getUsuario();
        this.f_ingreso = datosAlquiler.getF_ingreso();
        this.f_egreso = datosAlquiler.getF_salida();
        this.garagePartida = datosAlquiler.getLugarRetiro();
        this.garageLlegadaEst = datosAlquiler.getLugarDevolucion();
        this.estado = Estado.ACTIVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getF_egreso() {
        return f_egreso;
    }

    public void setF_egreso(LocalDate f_egreso) {
        this.f_egreso = f_egreso;
    }

    public LocalDate getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDate f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public Float getAdicionalCambioLugarFecha() {
        return adicionalCambioLugarFecha;
    }

    public void setAdicionalCambioLugarFecha(Float adicionalCambioLugarFecha) {
        this.adicionalCambioLugarFecha = adicionalCambioLugarFecha;
    }

    public Float getAdicionalInfraccionesOtro() {
        return adicionalInfraccionesOtro;
    }

    public void setAdicionalInfraccionesOtro(Float adicionalInfraccionesOtro) {
        this.adicionalInfraccionesOtro = adicionalInfraccionesOtro;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Garage getGaragePartida() {
        return garagePartida;
    }

    public void setGaragePartida(Garage garagePartida) {
        this.garagePartida = garagePartida;
    }

    public Garage getGarageLlegadaEst() {
        return garageLlegadaEst;
    }

    public void setGarageLlegadaEst(Garage garageLlegadaEst) {
        this.garageLlegadaEst = garageLlegadaEst;
    }

    public Garage getGarageLlegada() {
        return garageLlegada;
    }

    public void setGarageLlegada(Garage garageLlegada) {
        this.garageLlegada = garageLlegada;
    }

    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
        this.encargado = encargado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setAdicionalCambioLugarFecha(Alquiler alquiler, Suscripcion suscripcion) {
        Usuario usuario = suscripcion.getUsuario();
        if (usuario.getRol().equalsIgnoreCase("cliente")) {
            if (suscripcion.getUsuario().getId().equals(usuario.getId())) {
                String descripcion = suscripcion.getTipoSuscripcion().getDescripcion();
                if (this.garageLlegadaEst != this.garageLlegada)
                switch (descripcion) {
                    case "standard":
                        adicionalCambioLugarFecha=adicionalCambioLugarFecha+500f;
                        break;
                    case "premium":
                        adicionalCambioLugarFecha=adicionalCambioLugarFecha+400f;
                        break;
                    case "golden":
                        adicionalCambioLugarFecha=adicionalCambioLugarFecha+0f; //CAMBIAR POR EXCEPTION
                        break;
                }
            }
        }
    }


}



