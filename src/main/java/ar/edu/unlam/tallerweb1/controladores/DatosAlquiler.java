package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

public class DatosAlquiler {

    private Auto auto;
    private Cliente cliente;
    private String f_ingreso;
    private String f_regreso;

    public DatosAlquiler(Cliente cliente, Auto auto, String fechaInicio, String fechaRegreso) {

        this.auto=auto;
        this.cliente=cliente;
        this.f_ingreso=fechaInicio;
        this.f_regreso=fechaRegreso;

    }

    public Auto getAuto() {
        return auto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Long idCliente(){
        return cliente.getId();
    }

    public Long idAuto(){
        return auto.getId();
    }

    public String getF_Inicio() {
        return this.f_ingreso;
    }

    public String getF_Regreso() {
        return this.f_regreso;
    }
}
