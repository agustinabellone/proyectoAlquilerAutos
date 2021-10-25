package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Alquiler {

   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private LocalDateTime f_egreso;
    private LocalDateTime f_ingreso;
    private Float adicionalCambioLugarFecha;
    private Float adicionalInfraccionesOtro;

    @ManyToOne
    private Auto auto;
    @ManyToOne
    private Cliente cliente;
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




    public Alquiler() {
        this.estado=Estado.ACTIVO;
    }

    public Alquiler(Long id, Auto auto) {
        this.id=id;
        this.auto=auto;
        this.estado=Estado.ACTIVO;
    }

    public Alquiler(Auto auto, Cliente cliente) {
        this.auto=auto;
        this.cliente=cliente;
        this.estado=Estado.ACTIVO;
    }

    public Alquiler(DatosAlquiler DA ){
        this.f_ingreso=obtenerTipoFecha(DA.getIngresoF());
        this.f_egreso=obtenerTipoFecha(DA.getEgresoF());
        this.estado=Estado.ACTIVO;
    }

    private LocalDateTime obtenerTipoFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        return dateTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public LocalDateTime getF_egreso() {
        return f_egreso;
    }

    public void setF_egreso(LocalDateTime f_egreso) {
        this.f_egreso = f_egreso;
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


    public Encargado getEncargado() {
        return encargado;
    }

    public void setEncargado(Encargado encargado) {
       // this.encargado.getGarage().equals(gara) DUDA
    }

    public Garage getGaragePartida() {
        return garagePartida;
    }

    public void setGaragePartida(Garage garageInicio) {
        this.garagePartida = garageInicio;
    }

    public Garage getGarageLlegadaEst() {
        return garageLlegadaEst;
    }

    public void setGarageLlegadaEst(Garage garageFinEst) {
        this.garageLlegadaEst = garageFinEst;
    }

    public Garage getGarageLlegada() {
        return garageLlegada;
    }

    public void setGarageLlegada(Garage garageFin) {
        this.garageLlegada = garageFin;
    }

    public Float getAdicionalCambioLugarFecha() {
        return adicionalCambioLugarFecha;
    }

    public void setAdicionalCambioLugarFecha() {
        String descripcion = this.cliente.getSuscripcion().getTipoSuscripcion().getDescripcion();

        switch(descripcion) {
            case "standard":
                this.adicionalCambioLugarFecha+=500f;
            case "premium":
                this.adicionalCambioLugarFecha+=400f;
            case "golden":
                this.adicionalCambioLugarFecha+=0f; //CAMBIAR POR EXCEPTION
        }
    }

    public Float getAdicionalInfraccionesOtro() {
        return adicionalInfraccionesOtro;
    }

    public void setAdicionalInfraccionesOtro(Float adicionalInfraccionesOtro) {
        this.adicionalInfraccionesOtro = adicionalInfraccionesOtro;
    }
}
