package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public class DatosEnvioAMantenimiento {

    private Auto auto;
    private String fechaInicial;
    private Usuario usuario;


    public DatosEnvioAMantenimiento() {
    }

    public DatosEnvioAMantenimiento(Auto auto, String fechaInicial, Usuario usuario) {
        this.usuario = usuario;
        this.auto = auto;
        this.fechaInicial = fechaInicial;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
