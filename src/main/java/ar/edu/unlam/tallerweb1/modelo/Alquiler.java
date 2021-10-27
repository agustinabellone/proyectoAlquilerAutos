package ar.edu.unlam.tallerweb1.modelo;


import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Alquiler {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime f_egreso;
    private LocalDateTime f_ingreso;
    private Float adicionalCambioLugarFecha;
    private Float adicionalInfraccionesOtro;

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

    private String ingresoF;
    private String egresoF;

    public Alquiler(Long id,LocalDateTime f_ingreso,LocalDateTime f_regreso,Auto auto,Usuario usuario){
        this.id=id;
        this.f_ingreso=f_ingreso;
        this.f_egreso=f_regreso;
        this.auto=auto;
        this.usuario=usuario;
    }


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

    public Alquiler(DatosAlquiler DA) {
        // this.f_ingreso=obtenerTipoFecha(DA.getIngresoF());
        //this.f_egreso=obtenerTipoFecha(DA.getEgresoF());
        this.estado = Estado.ACTIVO;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getF_egreso() {
        return f_egreso;
    }

    public void setF_egreso(LocalDateTime f_egreso) {
        this.f_egreso = f_egreso;
    }

    public LocalDateTime getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDateTime f_ingreso) {
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

    public String getIngresoF() {
        return ingresoF;
    }

    public void setIngresoF(String ingresoF) {
        this.ingresoF = ingresoF;
    }

    public String getEgresoF() {
        return egresoF;
    }

    public void setEgresoF(String egresoF) {
        this.egresoF = egresoF;
    }


    public void setAdicionalCambioLugarFecha(Usuario usuario) {
        Suscripcion suscripcion = new Suscripcion();
        if (usuario.getRol().equalsIgnoreCase("cliente")) {
            if (suscripcion.getUsuario().getId().equals(usuario.getId())) {
                String descripcion = suscripcion.getTipoSuscripcion().getDescripcion();
                switch (descripcion) {
                    case "standard":
                        this.adicionalCambioLugarFecha += 500f;
                    case "premium":
                        this.adicionalCambioLugarFecha += 400f;
                    case "golden":
                        this.adicionalCambioLugarFecha += 0f; //CAMBIAR POR EXCEPTION
                }
            }
        }


    }


}

