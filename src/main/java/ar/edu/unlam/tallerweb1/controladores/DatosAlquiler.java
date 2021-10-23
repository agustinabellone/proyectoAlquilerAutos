package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class DatosAlquiler {

    private Auto auto;
    private Usuario usuario;
    private String f_ingreso;
    private String f_regreso;

    public DatosAlquiler(Usuario usuario, Auto auto, String fechaInicio, String fechaRegreso) {

        this.auto=auto;
        this.usuario = usuario;
        this.f_ingreso=fechaInicio;
        this.f_regreso=fechaRegreso;

    }

    public Auto getAuto() {
        return auto;
    }

    public Usuario getCliente() {
        return usuario;
    }

    public String getF_Inicio() {
        return this.f_ingreso;
    }

    public String getF_Regreso() {
        return this.f_regreso;
    }
}
