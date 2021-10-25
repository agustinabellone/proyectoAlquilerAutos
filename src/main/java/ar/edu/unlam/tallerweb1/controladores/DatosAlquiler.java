package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class DatosAlquiler {

    @OneToOne
    private Cliente cliente;
    @ManyToOne
    private Auto auto;
    private String ingresoF;
    private String egresoF;

    public DatosAlquiler(Cliente cliente, Auto auto, String fechaInicio, String fechaRegreso) {
        this.cliente=cliente;
        this.auto=auto;
        this.ingresoF=fechaRegreso;
        this.egresoF=fechaInicio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
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




}
