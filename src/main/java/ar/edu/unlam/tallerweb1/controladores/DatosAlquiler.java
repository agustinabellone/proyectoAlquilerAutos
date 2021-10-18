package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DatosAlquiler {

    private Auto auto;
    private Cliente cliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_ingreso;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_regreso;

    public DatosAlquiler(Cliente cliente, Auto auto, LocalDate fechaInicio, LocalDate fechaRegreso) {

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

    public LocalDate getF_Inicio() {
        return this.f_ingreso;
    }

    public LocalDate getF_Regreso() {
        return this.f_regreso;
    }
}
