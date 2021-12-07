package ar.edu.unlam.tallerweb1.modelo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Auto auto;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha_inicio_mantenimiento;

    private LocalDate fecha_fin_mantenimiento;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public LocalDate getFecha_inicio_mantenimiento() {
        return fecha_inicio_mantenimiento;
    }

    public void setFecha_inicio_mantenimiento(LocalDate fecha_inicio) {
        this.fecha_inicio_mantenimiento = fecha_inicio;
    }

    public LocalDate getFecha_fin_mantenimiento() {
        return fecha_fin_mantenimiento;
    }

    public void setFecha_fin_mantenimiento(LocalDate fecha_fin_mantenimiento) {
        this.fecha_fin_mantenimiento = fecha_fin_mantenimiento;
    }
}
