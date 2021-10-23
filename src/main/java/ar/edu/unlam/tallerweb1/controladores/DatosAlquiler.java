package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import ar.edu.unlam.tallerweb1.modelo.Cliente;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DatosAlquiler {

    private Auto auto;
    private Cliente cliente;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_salida;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_ingreso;

    public DatosAlquiler(Cliente cliente, Auto auto, LocalDate fechaSalida, LocalDate fechaIngreso) {

        this.auto=auto;
        this.cliente=cliente;
        this.f_salida=fechaSalida;
        this.f_ingreso=fechaIngreso;

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

    public LocalDate getF_salida() {
        return f_salida;
    }

    public void setF_salida(LocalDate f_salida) {
        this.f_salida = f_salida;
    }

    public LocalDate getF_ingreso() {
        return f_ingreso;
    }

    public void setF_ingreso(LocalDate f_ingreso) {
        this.f_ingreso = f_ingreso;
    }

}
