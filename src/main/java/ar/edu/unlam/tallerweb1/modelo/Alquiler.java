package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private Float adicionalKilometraje = 0.0f;
    private Float adicionalCondiciones = 0.0f;
    private Boolean estadoValoracionAuto = Boolean.FALSE;
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

    private Estado estado; //ACTIVO O FINALIZADO

    @ManyToOne
    private Usuario encargado;
    //POST DEVOLUCION ALQUILER TERMINADO
    private String comentario;
    private Boolean entregadoEnCondiciones;


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
        this.encargado = datosAlquiler.getLugarDevolucion().getEncargado();
        this.usuario = datosAlquiler.getUsuario();
        this.f_ingreso = datosAlquiler.getF_ingreso();
        this.f_egreso = datosAlquiler.getF_salida();
        this.garagePartida = datosAlquiler.getLugarRetiro();
        this.garageLlegada = datosAlquiler.getLugarDevolucion();
        this.garageLlegadaEst = datosAlquiler.getLugarDevolucion();
        this.estado = Estado.ACTIVO;
        this.estadoValoracionAuto = Boolean.FALSE;
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


    public Usuario getEncargado() {
        return encargado;
    }

    public void setEncargado(Usuario encargado) {
        this.encargado = encargado;
    }


    public Float getAdicionalKilometraje() {
        return adicionalKilometraje;
    }

    public Boolean getEstadoValoracionAuto() {
        return estadoValoracionAuto;
    }

    public void setEstadoValoracionAuto(Boolean estadoValoracionAuto) {
        this.estadoValoracionAuto = estadoValoracionAuto;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setAdicionalCambioLugarFecha(Float adicionalCambioLugarFechaParam) {
        adicionalCambioLugarFecha = adicionalCambioLugarFecha + adicionalCambioLugarFechaParam;
    }

    public void setAdicionalKilometraje(Suscripcion suscripcion, Float kmSobrepasados) {
        for(int i=0; i<kmSobrepasados; i++){
            this.adicionalKilometraje = adicionalKilometraje + suscripcion.getTipoSuscripcion().getValorPorKmAdicional();
        }
    }

    public void setAdicionalCondiciones(Float adicionalCondicionesNuevo) {
        this.adicionalCondiciones = this.adicionalCondiciones + adicionalCondicionesNuevo;
    }

    public Float getAdicionalCondiciones() {
        return adicionalCondiciones;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Boolean getEntregadoEnCondiciones() {
        return entregadoEnCondiciones;
    }

    public void setEntregadoEnCondiciones(Boolean entregadoEnCondiciones) {
        this.entregadoEnCondiciones = entregadoEnCondiciones;
    }


}




