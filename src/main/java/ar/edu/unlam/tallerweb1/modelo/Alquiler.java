package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;
<<<<<<< HEAD

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
=======
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

>>>>>>> master

@Entity
public class Alquiler {

<<<<<<< HEAD
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
    private Usuario usuario;
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

    private LocalDateTime obtenerTipoFecha(String fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(fecha, formatter);
        return dateTime;
    }


=======
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

>>>>>>> master
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

<<<<<<< HEAD

    public LocalDateTime getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDateTime f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

=======
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

>>>>>>> master
    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

<<<<<<< HEAD
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario cliente) {
        this.usuario = cliente;
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

    public Float getAdicionalInfraccionesOtro() {
        return adicionalInfraccionesOtro;
    }

    public void setAdicionalInfraccionesOtro(Float adicionalInfraccionesOtro) {
        this.adicionalInfraccionesOtro = adicionalInfraccionesOtro;
    }
}
=======
    public Usuario getCliente() {
        return usuario;
    }

    public void setCliente(Usuario cliente) {
        this.usuario = usuario;
    }
}
>>>>>>> master
