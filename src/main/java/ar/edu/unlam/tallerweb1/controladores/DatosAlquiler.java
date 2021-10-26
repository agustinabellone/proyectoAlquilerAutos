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

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(String f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public String getF_regreso() {
        return f_regreso;
    }

    public void setF_regreso(String f_regreso) {
        this.f_regreso = f_regreso;
    }
}
