package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Auto;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

<<<<<<< HEAD
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class DatosAlquiler {

=======
public class DatosAlquiler {

    private Auto auto;
    private Usuario usuario;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_salida;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate f_ingreso;

    public DatosAlquiler(Usuario usuario, Auto auto, LocalDate fechaSalida, LocalDate fechaIngreso) {
        this.auto = auto;
        this.usuario = usuario;
        this.f_salida = fechaSalida;
        this.f_ingreso = fechaIngreso;
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

>>>>>>> master
}
