package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;

import java.time.LocalDateTime;

public class DatosAlquiler {

    private Cliente cliente;
    private Auto auto;
    private LocalDateTime f_ingreso;
    private LocalDateTime f_regreso;

    public DatosAlquiler(Cliente cliente, Auto auto, LocalDateTime fechaInicio, LocalDateTime fechaRegreso) {
        this.cliente=cliente;
        this.auto=auto;
        this.f_ingreso=fechaInicio;
        this.f_regreso=fechaRegreso;
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

    public LocalDateTime getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDateTime f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

    public LocalDateTime getF_regreso() {
        return f_regreso;
    }

    public void setF_regreso(LocalDateTime f_regreso) {
        this.f_regreso = f_regreso;
    }


}
