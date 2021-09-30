package ar.edu.unlam.tallerweb1.modelo;

import ar.edu.unlam.tallerweb1.controladores.DatosAlquiler;

public class Alquiler {

    private Auto auto;
    private Cliente cliente;
    private String f_ingreso;
    private String f_regreso;

    public Alquiler() {
    }

    public Alquiler(DatosAlquiler DA ){

        this.auto=DA.getAuto();
        this.cliente=DA.getCliente();
        this.f_ingreso=DA.getF_Inicio();
        this.f_regreso=DA.getF_Regreso();

    }

    public Auto getAuto() {
        return this.auto;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public String getF_Inicio() {
        return this.f_ingreso;
    }

    public String getF_Regreso() {
        return this.f_regreso;
    }
}
